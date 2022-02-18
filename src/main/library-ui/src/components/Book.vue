<template>
  <div>
    <h3>Book Details</h3>
    <div class="container">
      <form @submit="validateAndSubmit">
        <div v-if="errors.length">
          <div
              class="alert alert-warning"
              v-bind:key="index"
              v-for="(error, index) in errors"
          ></div>
        </div>
        <fieldset class="form-group">
          <label>Id</label>
          <input type="text" class="form-control" v-model="id" disabled />
        </fieldset>
        <fieldset class="form-group">
          <label>Title</label>
          <input type="text" class="form-control" v-model="title" />
        </fieldset>
        <fieldset class="form-group">
          <label>Author</label>
          <input type="text" class="form-control" v-model="author" />
        </fieldset>
        <fieldset class="form-group">
          <label>Genre</label>
          <v-select
              :options="genre"
              label="genre"
          ></v-select>
          <input type="text" class="form-control" v-model="genre" />
        </fieldset>
        <button class="btn btn-success" type="submit">Save</button>
      </form>
    </div>
  </div>
</template>

<script>
import BooksService from "../service/BooksService";
export default {
  name: "bookDetails",
  data() {
    return {
      title: "n/a",
      author: "n/a",
      genre: "n/a",
      errors: []
    };
  },
  computed: {
    id() {
      return this.$route.params.id;
    }
  },
  methods: {
    refreshBookDetails() {
      BooksService.retrieveBook(this.id).then(res => {
        this.title = res.data.title;
        this.author = res.data.author;
        this.genre = res.data.genre;
      });
    },
    validateAndSubmit(e) {
      e.preventDefault();

      this.errors = [];
      if (!this.title) {
        this.errors.push("Enter valid values");
      } else if (this.title.length < 1) {
        this.errors.push("Enter some title");
      }

      if (this.errors.length === 0) {
        if (this.id === undefined) {
          BooksService.createBook( {
            title: this.title,
            author: this.author,
            genre: this.genre
          }).then(() => {
            this.$router.push("/books");
          });
        }  else {
          BooksService.updateBook(this.id, this.title, {
            id: this.id,
            title: this.title
          }).then(() => {
            this.$router.push("/books");
          });
        }
      }
    }
  },
  created() {
    this.refreshBookDetails();
  }
};
</script>

<style>
</style>