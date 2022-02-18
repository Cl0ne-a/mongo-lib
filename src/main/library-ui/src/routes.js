import Vue from "vue";
import Router from "vue-router";

Vue.use(Router);

const router = new Router({
    mode: "history",
    routes: [
        {
            path: "/",
            name: "Home",
            component: () => import("./components/BooksList")
        },
        {
            path: "/books",
            name: "Books List",
            component: () => import("./components/BooksList")
        },
        {
            path: "/books/:id",
            name: "Book Details",
            component: () => import("./components/Book")
        },
        {
            path: "/books/:id/:title",
            name: "Book update",
            component: () => import("./components/Book")
        },
        {
            path: "/newbook",
            name: "New book",
            component: () => import("./components/Book")
        }
    ]
});

export default router;