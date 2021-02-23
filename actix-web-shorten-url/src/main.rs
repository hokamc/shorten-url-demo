mod shorten_url;

use actix_web::{HttpServer, App, Responder, get, HttpResponse, http};
use std::io;
use crate::shorten_url::app_state::AppState;

#[actix_web::main]
async fn main() -> io::Result<()> {
    HttpServer::new(|| {
        let app_state = AppState::new(redis::Client::open("redis://127.0.0.1:6379").unwrap());

        App::new()
            .data(app_state.clone())
            .service(shorten_url::service::create_url)
            .service(shorten_url::service::redirect)
    })
        .bind("127.0.0.1:8080")?
        .run()
        .await
}
