<template>
  <div class="container-fluid" style="padding: 0px 180px">
    <div class="display-4 underline-illuminating mb-3" style="position: relative; bottom: 140px">
      나의 관심단지
    </div>

    <div class="container-fluid mb-5 d-flex">
      <!--
      <img class="mb-5" src="https://picsum.photos/1000/300" />
      -->
      <div id="chart" class="mr-5"><bookmark-chart /></div>
      <!-- <canvas
        class="statistics-charts-line"
        ref="lineChart"
        width="500"
        height="400"
      ></canvas> -->
      <!-- <b-row class="mb-5">
        <b-col cols="6"> -->
      <!-- <div>{{ bookmarkInfoDtos }}</div> -->
      <div>
        <bookmark-list-item
          v-for="bookmarkInfoDto in bookmarkInfoDtos"
          :key="bookmarkInfoDto.aptCode"
          v-bind="bookmarkInfoDto"
        />
      </div>
      <!-- </b-col>
      </b-row> -->
    </div>
  </div>
</template>

<script>
import { listBookmark } from "@/api/bookmark.js";
import { mapState } from "vuex";
import BookmarkListItem from "@/components/bookmark/item/BookmarkListItem";
import BookmarkChart from "@/components/bookmark/item/BookmarkChart.vue";
const memberStore = "memberStore";

export default {
  name: "BookmarkList",
  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo"]),
  },
  components: {
    BookmarkListItem,
    BookmarkChart,
  },
  data() {
    return {
      bookmarkInfoDtos: [],
    };
  },
  created() {
    // console.log(this.userInfo);
    listBookmark(
      this.userInfo.userId,
      //"1111",
      (response) => {
        console.log(response);
        this.bookmarkInfoDtos = response.data;
      },
      (error) => {
        console.log(error);
      },
    );
  },
};
</script>

<style></style>
