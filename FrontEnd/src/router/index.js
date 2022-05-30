import Vue from "vue";
import VueRouter from "vue-router";
import HomeView from "../views/HomeView.vue";

import store from "@/store/index.js";

Vue.use(VueRouter);

// https://router.vuejs.org/kr/guide/advanced/navigation-guards.html
const onlyAuthUser = async (to, from, next) => {
  const checkUserInfo = store.getters["memberStore/checkUserInfo"];
  const getUserInfo = store._actions["memberStore/getUserInfo"];
  let token = sessionStorage.getItem("access-token");
  if (checkUserInfo == null && token) {
    await getUserInfo(token);
  }
  if (checkUserInfo === null) {
    alert("로그인이 필요한 페이지입니다.");
    next({ name: "signIn" });
  } else {
    next();
  }
};

const routes = [
  {
    path: "/",
    name: "home",
    component: HomeView,
  },
  {
    path: "/user",
    name: "user",
    component: () => import("@/views/MemberView.vue"),
    children: [
      {
        path: "signin",
        name: "signIn",
        component: () => import("@/components/user/MemberLogin.vue"),
      },
      {
        path: "singup",
        name: "signUp",
        component: () => import("@/components/user/MemberRegister.vue"),
      },
      {
        path: "modify",
        name: "modify",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/user/MemberModify.vue"),
      },
    ],
  },
  {
    path: "/community",
    name: "community",
    component: () => import("@/views/CommunityView.vue"),
    redirect: "/community/home",
    children: [
      {
        path: "home",
        name: "communityHome",
        component: () => import("@/components/community/CommunityHome.vue"),
      },
    ],
  },
  {
    path: "/board",
    name: "board",
    component: () => import("@/views/BoardView.vue"),
    redirect: "/board/list",
    children: [
      {
        path: "list",
        name: "boardList",
        component: () => import("@/components/board/BoardList.vue"),
      },
      {
        path: "write",
        name: "boardRegister",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/board/BoardRegister.vue"),
      },
      {
        path: "detail/:articleno",
        name: "boardDetail",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/board/BoardDetail.vue"),
      },
      {
        path: "modify/:articleno",
        name: "boardModify",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/board/BoardModify.vue"),
      },
    ],
  },
  {
    path: "/support",
    name: "support",
    component: () => import("@/views/SupportView.vue"),
    redirect: "/support/home",
    children: [
      {
        path: "home",
        name: "supportHome",
        component: () => import("@/components/support/SupportHome.vue"),
      },
      {
        path: "noticedetail",
        name: "noticeDetail",
        component: () => import("@/components/support/NoticeDetail.vue"),
      },
      {
        path: "noticemodify",
        name: "noticeModify",
        component: () => import("@/components/support/NoticeModify.vue"),
      },
      {
        path: "noticelist",
        name: "noticeList",
        component: () => import("@/components/support/NoticeList.vue"),
      },
      {
        path: "noticeregister",
        name: "noticeRegister",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/support/NoticeRegister.vue"),
      },
      {
        path: "qnadetail/:qnaId",
        name: "qnaDetail",
        component: () => import("@/components/support/QnaDetail.vue"),
      },
      {
        path: "qnalist",
        name: "qnaList",
        component: () => import("@/components/support/QnaList.vue"),
      },
      {
        path: "qnamodify",
        name: "qnaModify",
        component: () => import("@/components/support/QnaModify.vue"),
      },
      {
        path: "qnaregister",
        name: "qnaRegister",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/support/QnaRegister.vue"),
      },
    ],
  },
  {
    path: "/bookmark",
    name: "bookmark",
    beforeEnter: onlyAuthUser,
    component: () => import("@/views/BookmarkView.vue"),
    redirect: "/bookmark/home",
    children: [
      {
        path: "home",
        name: "bookmarkHome",
        component: () => import("@/components/bookmark/BookmarkHome.vue"),
      },
    ],
  },
  {
    path: "/search",
    name: "search",
    component: () => import("@/views/SearchView.vue"),
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
