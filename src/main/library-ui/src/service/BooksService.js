import axios from "axios";

const BOOKS = "books";
const LIBRARY_URL = "http://localhost:8080";
const BOOKS_API_URL = `${LIBRARY_URL}/api/${BOOKS}`;

class BooksService {
    retrieveAllBooks() {
        return axios.get(`${BOOKS_API_URL}`);
    }
}

export default new BooksService();
