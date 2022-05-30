<template>
  <b-col cols="2">
    <div>
      <b-img thumbnail fluid src="https://picsum.photos/250/250/" alt="Image 1"></b-img>
      <div>
        <h5>
          안녕하세요!
          <span style="font-size: smaller; color: orange">[2]</span>
        </h5>
        ssafy1<br />
        2022/05/22<br />
      </div>
    </div>
  </b-col>
</template>

<script>
import moment from "moment";

export default {
  name: "BoardListItem",
  props: {
    articleno: Number,
    subject: String,
    regtime: String,
    userid: String,
  },
  filters: {
    dateFormat(regtime) {
      return moment(new Date(regtime)).format("YY.MM.DD");
    },
  },
};
</script>

<style></style>
