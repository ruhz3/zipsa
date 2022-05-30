<template>
  <b-list-group-item>
    <div class="mb-3">
      <span class="h5 mr-4">{{ userId }}</span>
      <span>{{ date }}</span>
      <span @click="deleteComment" class="btn sm"><b-icon-trash></b-icon-trash></span>
    </div>
    <p class="mb-0">{{ content }}</p>
  </b-list-group-item>
</template>

<script>
import { deleteQnaComment } from "@/api/qna";
export default {
  name: "QnaCommentItem",
  props: {
    commentId: String,
    qnaId: String,
    content: String,
    userId: String,
    date: String,
  },
  methods: {
    deleteComment() {
      deleteQnaComment(
        this.commentId, // params로 다시 시도 요망
        () => {
          this.$router.go();
        },
        (error) => {
          console.log(error);
        },
      );
    },
  },
};
</script>

<style scoped>
.comment-count {
  color: darkorange;
}
</style>
