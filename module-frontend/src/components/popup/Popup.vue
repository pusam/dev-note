<template>
    <div v-if="popupStore.isOpen" class="popup">
        <div class="popup-content">
            <h2>{{ popupStore.title }}</h2>
            <p>{{ popupStore.msg }}</p>
            <button @click="popupStore.closePopup">닫기</button>
        </div>
    </div>
</template>

<script setup>
import { usePopupStore } from "@/store/popupStore";
const popupStore = usePopupStore(); // 스토어 인스턴스 가져오기

</script>

<style scoped>
.popup {
    position: fixed;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    background-color: rgba(0, 0, 0, 0.5);
    display: flex;
    justify-content: center;
    align-items: center;
}

.popup-content {
    background: white;
    padding: 20px;
    border-radius: 10px;
}
</style>