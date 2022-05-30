<template>
  <div class="container-fluid-xl">
    <div class="container-fluid" style="padding-left: 100px">
      <div class="mt-5">
        <p class="display-3">
          <span class="display-2 underline-illuminating">집</span>, 알아보고
          <span class="display-2 underline-illuminating">사</span>세요!
        </p>
        <p class="display-4">구매를 위한, 정확한 데이터를 제공합니다.</p>
      </div>

      <div class="my-5">
        <router-link :to="{ name: 'search' }"
          ><img
            src="@/assets/house_menu.png"
            style="height: 80px"
            class="mr-1"
            v-animate-css.hover="'headShake'"
        /></router-link>
        <router-link :to="{ name: 'community' }"
          ><img
            src="@/assets/community_menu.png"
            style="height: 80px"
            class="mr-1"
            v-animate-css.hover="'headShake'"
        /></router-link>
        <router-link :to="{ name: 'support' }"
          ><img
            src="@/assets/service_menu.png"
            style="height: 80px"
            class="mr-1"
            v-animate-css.hover="'headShake'"
        /></router-link>
        <router-link :to="{ name: 'interest' }"
          ><img
            src="@/assets/inbox_menu.png"
            style="height: 80px"
            class="mr-1"
            v-animate-css.hover="'headShake'"
        /></router-link>
      </div>
    </div>
    <div>
      <b-embed type="video" aspect="16by9" loop muted controls>
        <source src="@/assets/typo.mp4" type="video/mp4" />
      </b-embed>
    </div>
    <div class="container-fluid p-5">
      <div class="d-flex">
        <div class="col-6">
          <!--공지사항 최근 글 5개-->
          <div class="container-fluid mb-5">
            <div class="mb-5">
              <div class="mb-3">
                <span class="h2">공지사항</span>
                <span class="ml-4"
                  ><router-link :to="{ name: 'support' }" class="link"
                    >더보기<b-icon-arrow-right /></router-link
                ></span>
              </div>
              <b-table-simple
                id="notice-recent-table"
                :hover="true"
                :outlined="true"
                :head-variant="'light'"
                caption-top
              >
                <b-thead>
                  <b-tr>
                    <b-th>번호</b-th>
                    <b-th>제목</b-th>
                    <b-th>작성일</b-th>
                    <b-th>작성자</b-th>
                  </b-tr>
                </b-thead>
                <tbody>
                  <notice-list-item
                    v-for="noticeInfoDto in noticeInfoDtos"
                    :key="noticeInfoDto.noticeId"
                    v-bind="noticeInfoDto"
                  />
                </tbody>
              </b-table-simple>
            </div>
          </div>
        </div>
        <div class="col-6">
          <div>
            <b-carousel
              id="carousel-fade"
              style="text-shadow: 0px 0px 2px #000"
              fade
              indicators
              img-width="1024"
              img-height="480"
            >
              <a
                href="http://www.molit.go.kr/USR/NEWS/m_71/lst.jsp?search_section=p_sec_2"
                target="_blank"
              >
                <b-carousel-slide
                  caption="국토교통부 뉴스"
                  img-src="https://picsum.photos/1024/480/?image=1048"
                ></b-carousel-slide>
              </a>
              <a href="https://land.naver.com/" target="_blank">
                <b-carousel-slide
                  caption="네이버 부동산"
                  img-src="https://picsum.photos/1024/480/?image=1029"
                ></b-carousel-slide>
              </a>
              <a href="https://rt.molit.go.kr/" target="_blank">
                <b-carousel-slide
                  caption="실거래가 공개시스템"
                  img-src="https://picsum.photos/1024/480/?image=1076"
                ></b-carousel-slide>
              </a>
            </b-carousel>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { listRecentNotice } from "@/api/notice.js";
import NoticeListItem from "@/components/support/item/NoticeListItem";

export default {
  name: "HomeView",
  props: {
    msg: String,
  },
  components: {
    NoticeListItem,
  },
  data() {
    return {
      noticeInfoDtos: [],
    };
  },
  created() {
    listRecentNotice(
      (response) => {
        this.noticeInfoDtos = response.data;
      },
      (error) => {
        console.log(error);
      },
    );
  },
};
</script>

<style scoped>
.underline-steelblue {
  display: inline-block;
  background: linear-gradient(180deg, rgba(255, 255, 255, 0) 70%, rgba(72, 190, 233, 0.3) 30%);
}
</style>
