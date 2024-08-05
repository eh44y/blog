import { BoardListItem } from "types/interface";

const latestBoardListMock: BoardListItem[] = [
  {
    boardNumber: 1,
    title: "API의 개념",
    content:
      "Application Programming Interface의 약자로 소프트웨어 응용 프로그램에서 다른 소프트웨어의 구성 요서 또는 서비스와 상호작용하기 위한 인터페이스를 제공하는 프로그래밍 기술이다.",
    boardTitleImage: "https://i.ytimg.com/vi/--GV-b2qUBA/maxresdefault.jpg",
    favoriteCount: 0,
    commentCount: 0,
    viewCount: 0,
    writeDateTime: "2024.05.10 16:00:00",
    writerNickname: "Diaz",
    writerProfileImage:
      "https://i.namu.wiki/i/ovpSOBRTsRaVXvigIydavPuFj2afmM61vrf7yZQR0dELuXuy15pUDfEm7DNHwe6slH592dwfDD0abxCvWu5aO5EPHmEVE9JF40uEF83BvVobuK-oCueIvHopVs8wLrC5MNgAXBIiH8EaK7t3ydM0ZQ.webp",
  },
  {
    boardNumber: 1,
    title: "MVC 패턴이란?",
    content:
      "Model, View, Controller로 이루어진 디자인 패턴으로 하나의 어플리케이션 구성 요소를 세가지로 구분한 패턴으로 사용자가 Controller에 접근하면 Model을 통해 데이터를 가져오고 View를 제어해 사용자에게 전달하는 것",
    boardTitleImage: "https://i.ytimg.com/vi/L3V_aUIDNHs/maxresdefault.jpg",
    favoriteCount: 0,
    commentCount: 0,
    viewCount: 0,
    writeDateTime: "2024.05.10 16:00:00",
    writerNickname: "44y",
    writerProfileImage:
      "https://pbs.twimg.com/profile_images/1133271907229405185/N2oMnb35_400x400.png",
  },
  {
    boardNumber: 1,
    title: "오버로딩과 오바라이딩이란?",
    content: `오버로딩은 같은 이름을 가진 메서드를 피라미터의 값을 다르게하여 여러개를 사용하는 것 
    오버라이딩은 상위 클래스에서 상속받은 메서드를 하위 클래스에서 재정의 하는 것`,
    boardTitleImage: null,
    favoriteCount: 0,
    commentCount: 0,
    viewCount: 0,
    writeDateTime: "2024.05.10 16:00:00",
    writerNickname: "Dimoo",
    writerProfileImage: null,
  },
];

export default latestBoardListMock;
