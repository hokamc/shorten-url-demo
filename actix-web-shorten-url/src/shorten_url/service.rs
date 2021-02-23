use actix_web::{post, get, HttpResponse, Responder, http};
use actix_web::web::{Json, Data, Path};
use serde::{Serialize, Deserialize};
use crate::shorten_url::app_state::AppState;
use redis::{Commands, RedisResult};
use crate::shorten_url::{encoder, constants};

#[derive(Serialize, Deserialize)]
pub struct CreateShortUrlRequest {
    url: String
}

#[derive(Serialize, Deserialize)]
pub struct CreateShortUrlResponse {
    url: String
}

#[post("/url")]
pub async fn create_url(request: Json<CreateShortUrlRequest>, app: Data<AppState>) -> HttpResponse {
    let mut redis = app.get_redis();
    let counter = redis.incr(constants::COUNTER_KEY, 1).unwrap();
    let encoded_url: String = encoder::encode(counter).unwrap();
    let result: RedisResult<String> = redis.set(encoded_url.clone(), request.url.clone());
    result.unwrap();
    HttpResponse::Ok().json(CreateShortUrlResponse { url: String::from(encoded_url) })
}

#[get("/{shorten_url}")]
pub async fn redirect(shorten_url: Path<String>, app: Data<AppState>) -> impl Responder{
    let mut redis = app.get_redis();
    let url: RedisResult<String> = redis.get(shorten_url.0);
    match url {
        Ok(value) => HttpResponse::Found().header(http::header::LOCATION, value).finish().into_body(),
        Err(_) => HttpResponse::NotFound().finish().into_body()
    }
}
