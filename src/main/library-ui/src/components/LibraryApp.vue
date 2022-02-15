<template>
  <div>
    <h1>Library Helpdesk</h1>
    <BooksList></BooksList>
  </div>
</template>

<script>
import BooksList from "@/components/BooksList";
export default {
  name: "LibraryApp",
  components: {BooksList}
}
</script>

<style scoped>

</style>