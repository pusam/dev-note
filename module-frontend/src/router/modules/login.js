import Login from "@/components/login/LoginPage.vue";
import Main from "@/components/MainLayout.vue";

export default [
    {
        path: "/",
        name : "index",
        component: Main
    },
    {
        path: "/login",
        name: "login",
        component : Login
    }
]
