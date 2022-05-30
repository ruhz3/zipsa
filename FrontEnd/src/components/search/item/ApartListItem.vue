<template>
  <b-button
    variant="outline-dark"
    class="w-100 text-left p-2"
    @click="emitReturnAptCode(), emitClickMapEvent()"
  >
    <div class="p-2">
      <span class="h4">{{ aptName }}</span>
      <span><b-icon-heart @click="registerBookmark" style="float: right"></b-icon-heart></span>
      <table class="mt-2">
        <tr>
          <th>위치</th>
          <td>&nbsp;&nbsp;{{ sidoName }} {{ gugunName }} {{ dongName }} {{ jibun }}</td>
        </tr>
        <tr>
          <th>준공년도</th>
          <td>&nbsp;&nbsp;{{ buildYear }}년</td>
        </tr>
        <tr>
          <th>최근가격</th>
          <td>&nbsp;&nbsp;{{ recentPrice }}</td>
        </tr>
      </table>
    </div>
  </b-button>
</template>

<script>
import moment from "moment";
import { mapState } from "vuex";
import { writeBookmark } from "@/api/bookmark";
const memberStore = "memberStore";
export default {
  name: "BoardListItem",
  props: {
    aptCode: Number,
    aptName: String,
    jibun: String,
    dongName: String,
    gugunName: String,
    sidoName: String,
    buildYear: Number,
    lat: String,
    lng: String,
    img: String,
    recentPrice: String,
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo"]),
  },
  filters: {
    dateFormat(regtime) {
      return moment(new Date(regtime)).format("YY.MM.DD");
    },
  },
  methods: {
    emitReturnAptCode() {
      this.$emit("return-apt-code", this.aptCode);
    },
    emitClickMapEvent() {
      this.$emit("click-map-event", this.lat, this.lng);
    },
    registerBookmark() {
      let params = {
        aptCode: this.aptCode,
        userId: this.userInfo.userId,
      };
      writeBookmark(
        params,
        () => {
          alert("관심단지로 등록했습니다.");
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
.list-item {
  width: 100%;
}
</style>
