use super::error::Errors;

const MAX_LENGTH: usize = 6;

const AVAILABLE_CHARS: [char; 62] = [
    '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
    'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
    'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
];

const MAX_SIZE: u64 = 56800235583;

pub fn encode(counter: u64) -> Result<String, Errors> {
    if counter > MAX_SIZE {
        return Result::Err(Errors::InvalidArguments("counter is larger than maximum number"));
    }

    let mut code: [char; MAX_LENGTH] = ['0'; MAX_LENGTH];
    let mut number = counter;
    let length = AVAILABLE_CHARS.len() as u64;
    for i in (0..MAX_LENGTH).rev() {
        if number > 0 {
            let value: usize = (number % length) as usize;
            number = number / length;
            code[i] = AVAILABLE_CHARS[value];
        } else {
            code[i] = AVAILABLE_CHARS[0];
        }
    }

    Result::Ok(code.iter().collect())
}

#[cfg(test)]
mod tests {
    use crate::shorten_url::encoder::{encode};

    #[test]
    fn encode0() {
        assert_eq!(encode(0), Result::Ok(String::from("000000")));
    }

    #[test]
    fn encode1() {
        assert_eq!(encode(1), Result::Ok(String::from("000001")));
    }

    #[test]
    fn encode61() {
        assert_eq!(encode(61), Result::Ok(String::from("00000z")));
    }

    #[test]
    fn encode62() {
        assert_eq!(encode(62), Result::Ok(String::from("000010")));
    }

    #[test]
    fn encode123456() {
        assert_eq!(encode(123456), Result::Ok(String::from("000W7E")));
    }

    #[test]
    fn encode56800235583() {
        assert_eq!(encode(56800235583), Result::Ok(String::from("zzzzzz")));
    }

    #[test]
    fn encode56800235584() {
        assert!(encode(56800235584).is_err());
    }
}
