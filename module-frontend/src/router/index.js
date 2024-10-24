import { createRouter, createWebHistory } from 'vue-router';
import login from "@/router/modules/login";

const router = createRouter( {
    history : createWebHistory(),
    routes : [
        ...login
    ]
});

export default router