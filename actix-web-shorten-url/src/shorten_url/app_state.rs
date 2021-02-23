use actix_web::Error;

#[derive(Clone)]
pub struct AppState {
    redis :redis::Client
}

impl AppState {
    pub fn new(redis: redis::Client) -> AppState {
        AppState {
            redis
        }
    }

    pub fn get_redis(&self) -> redis::Connection {
        self.redis.get_connection().unwrap()
    }
}
