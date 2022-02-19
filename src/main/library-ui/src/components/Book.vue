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
          <div id="v-model-author" class="author">
            <select v-model="authorent">
              <option disabled value="">Choose an option</option>
              <option>Aditya Bhargava</option>
              <option>Randall Munroe</option>
            </select>
            <span>Chosen: {{ this.authorent }}</span>
          </div>
        </fieldset>
        <fieldset class="form-group">
          <label>Genre</label>
          <div id="v-model-genre" class="genre">
            <select v-model="selected">
              <option disabled value="">Choose an option</option>
              <option>science</option>
              <option>comedy</option>
            </select>
            <span>Chosen: {{ this.selected }}</span>
          </div>

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
      authorent: '',
      selected: '',
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
            author: this.authorent,
            genre: this.selected
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