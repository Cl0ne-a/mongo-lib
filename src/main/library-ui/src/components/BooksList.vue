<template>
  <div class="container">
    <h3>All Bookss</h3>
    <div class="container">
      <table class="table">
        <thead>
        <tr>
          <th>Id</th>
          <th>Title</th>
          <th>Genre</th>
          <th>Author</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="book in books" v-bind:key="book.id">
          <td>{{ book.id }}</td>
          <td>{{ book.title }}</td>
          <td>{{ book.genre.genre }}</td>
          <td>{{ book.author.name }}</td>
        </tr>
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
      BOOKS: "books"
    };
  },
  methods: {
    refreshBooks() {
      BooksService.retrieveAllBooks()
          .then(response => {
            this.books = response.data;
          });
    }
  },
  created() {
    this.refreshBooks();
  }
};
</script>

<style>
</style>