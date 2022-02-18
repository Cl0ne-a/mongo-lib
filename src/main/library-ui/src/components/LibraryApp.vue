<template>
  <div>
    <h1>L i b r a r y</h1>
    <BooksList></BooksList>
  </div>
</template>

<script>
import BooksList from "@/components/BooksList";
export default {
  name: "library",
  components: {
    BooksList
  }
};
</script>

<style scoped>
</style>
