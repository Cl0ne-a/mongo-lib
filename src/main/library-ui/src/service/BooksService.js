import axios from "axios";

class BooksService {
    retrieveAllBooks() {
        return axios.get(`http://localhost:8080/api/books`);
    }
    deleteBook(id) {
        return axios.delete(`http://localhost:8080/api/books/${id}`)
    }
    retrieveBook(id) {
        return axios.get(`http://localhost:8080/api/books/${id}`);
    }
    updateBook(id, title) {
        return axios.put(`http://localhost:8080/api/books/${id}/${title}`, title);
    }
    createBook(book) {
            return axios.post(`http://localhost:8080/api/newbook`, book);
    }
}

export default new BooksService();
