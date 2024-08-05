import { CommentListItem } from "types/interface";

const commentListMock: CommentListItem[] = [
  {
    nickname: "Diaz",
    profileImage:
      "https://cdn.artivenews.co.kr/news/photo/202406/1405_8230_3643.jpg",
    writeDatetime: "2024.05.10 16:00:00",
    content:
      "Application Programming Interface의 약자로 소프트웨어 응용 프로그램에서 다른 소프트웨어의 구성 요서 또는 서비스와 상호작용하기 위한 인터페이스를 제공하는 프로그래밍 기술이다.",
  },
  {
    nickname: "44y",
    profileImage:
      "https://pbs.twimg.com/profile_images/1133271907229405185/N2oMnb35_400x400.png",
    writeDatetime: "2024.05.10 16:00:00",
    content:
      "Model, View, Controller로 이루어진 디자인 패턴으로 하나의 어플리케이션 구성 요소를 세가지로 구분한 패턴으로 사용자가 Controller에 접근하면 Model을 통해 데이터를 가져오고 View를 제어해 사용자에게 전달하는 것",
  },
  {
    nickname: "Dimoo",
    profileImage: null,
    writeDatetime: "2024.05.10 16:00:00",
    content: `오버로딩은 같은 이름을 가진 메서드를 피라미터의 값을 다르게하여 여러개를 사용하는 것 
    오버라이딩은 상위 클래스에서 상속받은 메서드를 하위 클래스에서 재정의 하는 것`,
  },
];

export default commentListMock;
