<template>
  <div class="container">
    <h3>All Books</h3>
    <div v-if="message" class="alert alert-success">
    {{message}}
  </div>
    <div class="container">
      <table class="table">
        <thead>
        <tr>
          <th>Id</th>
          <th>Title</th>
          <th>Genre</th>
          <th>Author</th>
          <th>Update</th>
          <th>Delete</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="book in books" v-bind:key="book.id">
          <td>{{ book.id }}</td>
          <td>{{ book.title }}</td>
          <td>{{ book.genre }}</td>
          <td>{{ book.author }}</td>
          <td><button class="btn btn-success" v-on:click="updateBookClicked(book.id)">Update</button></td>
          <td><button class="btn btn-warning" v-on:click="deleteBookClicked(book.id)">Delete</button></td>
        </tr>
        <div class="ui-button">
          <button class="btn btn-success" v-on:click="addBookClicked()">Add</button>
        </div>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import BooksService from "@/service/BooksService";

export default {
  name: "BooksList",
  data() {
    return {
      books: [],
      message: null
    };
  },
  methods: {
    refreshBooks() {
      BooksService.retrieveAllBooks()
          .then(response => {
            this.books = response.data;
          });
    },
    deleteBookClicked(id) {
      BooksService.deleteBook(id).then(() => {
        this.message = `Deleted book #${id}`;
        this.refreshBooks();
      });
    },
    updateBookClicked(id) {
      this.$router.push(`/books/${id}`);
    },
    addBookClicked() {
      this.$router.push(`/newbook`)
    }
  },
  created() {
    this.refreshBooks();
  }
};
</script>

<style>
</style>