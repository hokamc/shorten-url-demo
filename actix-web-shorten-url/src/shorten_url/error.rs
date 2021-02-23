
#[derive(Debug, Eq, PartialEq)]
pub enum Errors {
    InvalidArguments(&'static str)
}
