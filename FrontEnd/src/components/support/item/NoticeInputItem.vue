<!--공지사항 글쓰기할 때 보이는 input form-->
<template>
  <b-row class="mb-1">
    <b-col style="text-align: left">
      <b-form @submit="onSubmit" @reset="onReset">
        <b-form-group id="userid-group" label="작성자" label-for="userid">
          <b-form-input
            id="userid"
            disabled
            v-model="noticeInfoDto.userId"
            type="text"
            required
            placeholder="작성자를 입력하세요."
          ></b-form-input>
        </b-form-group>

        <b-form-group id="subject-group" label="제목" label-for="subject">
          <b-form-input
            id="subject"
            v-model="noticeInfoDto.title"
            type="text"
            required
            placeholder="제목을 입력하세요."
          ></b-form-input>
        </b-form-group>

        <b-form-group id="content-group" label="내용" label-for="content">
          <b-form-textarea
            id="content"
            v-model="noticeInfoDto.content"
            placeholder="내용을 입력하세요."
            rows="10"
            max-rows="15"
          ></b-form-textarea>
        </b-form-group>

        <b-button
          type="submit"
          class="m-1"
          style="background-color: #f6e45e; color: black; border: 0px"
          v-if="this.type === 'register'"
          >글작성</b-button
        >
        <b-button
          type="submit"
          class="m-1"
          style="background-color: #f6e45e; color: black; border: 0px"
          v-else
          >글수정</b-button
        >
        <b-button
          type="reset"
          variant="danger"
          class="m-1"
          style="background-color: #999a9c; color: white; border: 0px"
          >초기화</b-button
        >
      </b-form>
    </b-col>
  </b-row>
</template>

<script>
import { writeNotice, getNotice, modifyNotice } from "@/api/notice";
import { mapState } from "vuex";

const memberStore = "memberStore";

export default {
  name: "NoticeInputItem",
  data() {
    return {
      //
      noticeInfoDto: {
        noticeId: "",
        userId: "",
        title: "",
        content: "",
      },
    };
  },
  props: {
    type: { type: String },
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  created() {
    if (this.type === "modify") {
      getNotice(
        this.$route.params.noticeId,
        ({ data }) => {
          // this.article.articleno = data.article.articleno;
          // this.article.userid = data.article.userid;
          // this.article.subject = data.article.subject;
          // this.article.content = data.article.content;
          this.noticeInfoDto = data;
        },
        (error) => {
          console.log(error);
        },
      );
      // this.isUserid = true;
    } else {
      this.noticeInfoDto.userId = this.userInfo.userId;
    }
  },
  methods: {
    onSubmit(event) {
      event.preventDefault();
      let err = true;
      let msg = "";
      !this.noticeInfoDto.userId &&
        ((msg = "작성자 입력해주세요"), (err = false), this.$refs.userId.focus());
      err &&
        !this.noticeInfoDto.title &&
        ((msg = "제목 입력해주세요"), (err = false), this.$refs.title.focus());
      err &&
        !this.noticeInfoDto.content &&
        ((msg = "내용 입력해주세요"), (err = false), this.$refs.content.focus());

      if (!err) alert(msg);
      else this.type === "register" ? this.registNotice() : this.modifyNotice();
    },
    onReset(event) {
      event.preventDefault();
      this.noticeInfoDto.noticeId = 0;
      this.noticeInfoDto.title = "";
      this.noticeInfoDto.content = "";
      this.$router.push({ name: "support" });
    },
    registNotice() {
      writeNotice(
        {
          userId: this.noticeInfoDto.userId,
          title: this.noticeInfoDto.title,
          content: this.noticeInfoDto.content,
        },
        ({ data }) => {
          let msg = "등록 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "등록이 완료되었습니다.";
          }
          alert(msg);
          this.moveList();
        },
        (error) => {
          console.log(error);
        },
      );
    },
    modifyNotice() {
      modifyNotice(
        {
          noticeId: this.noticeInfoDto.noticeId,
          userId: this.noticeInfoDto.userId,
          title: this.noticeInfoDto.title,
          content: this.noticeInfoDto.content,
        },
        ({ data }) => {
          let msg = "수정 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "수정이 완료되었습니다.";
          }
          alert(msg);
          // 현재 route를 /list로 변경.
          this.$router.push({ name: "support" });
        },
        (error) => {
          console.log(error);
        },
      );
    },
    moveList() {
      this.$router.push({ name: "support" });
    },
  },
};
</script>

<style></style>
