현재 feign의 문제점은 요청하는 http method와 같은 메소드를 가지고 있어야한다는 것이다.
post 요청 -> feign get사용 이 불가하는 것이다.
post 요청 -> feign post 사용 해야하만 가능하다.
따라서 이를 kafka로 변경해보자

카프카의 단점은 메세지를 받고 받은 주체가 다시 보내는것은 가능해도
보내는 주체가 받지는 못한다.
메세지를 보내고 받기를 원하면 mq를 쓰는 방법보다 read-only db를 넣는방법을 사용하는것이 좋다.

password encoder는 서비스끼리 혼용해서 쓸수없다.
bankbook에서 만든 password는 bankbook으로 보내서 pw 판별해야한다.
이것이 싫다면 암호화 하지 않고 써야한다.

record 이외의 송금과 atm에 commonutils에 넣는다.
public static int createNowYear() {
        return LocalDate.now().getYear();
    }

    public static Month createNowMonth() {
        return LocalDate.now().getMonth();
    }

record를 만든 후에 송금과 atm은 각각 record를 똑같이 저장하는 read-only db를 만든다.
복제 방법은 record가 추가 될때마다 record로 카프카를 통해 보내고,
record를 저장한 후에 record kafka는 각각 송금과 atm으로 해당 값을 전달하여 저장시킨다.
약간 희안한 구조로 되어있는데, 이는 복제 db먼저 저장하는 것이 아닌 진짜 crud db인 원본 record에 먼저 저장하고 복제 db(read-only db)에는 비동기로 처리하여 저장하는 것이다.
물론 atm과 송금 모두 jpa는 필요로 한다.(저장해야해서)
다만 절대로 read이외의 쿼리를 날리지 않는다.

테스트 코드
토픽은 문서화 해서 저장하기
위키와 전체 문서화
db 인덱스 걸기


yml에서 path랑 method잘 집어넣기
이중에서 authorizaionfilter 안걸꺼면 잘 빼기
후에 fallback controller 에 컨트롤러 넣기
카프카는 컨슈머 그룹아이디 intelligentBankGroup 로 지정하기
에러는 아래와 같이 처리한다.
requet dto 키를 이용해서 해당 값이 없다면
errordto를 꺼내서 메세지를 바인딩 받으면된다.

api조회나 다른 db테이블의 id를 저장하여 이중으로 조회쿼리를 날리는 방식은 성능에 좋지 않다.
당연히 조인이 빠르다.
그런데 msa는 db를 분리하는것이 msa가 추구하는 주 목적이다.(낮을 결합성)

따라서 해당 프로젝트에서는 각 서비스 별로 db를 분리하고 필요시 다른 db에서 조회하고 feign client나 kafka로 데이터를 전송하는 방식을 채택했다.
다만 서비스가 성장하고 성장해 트래픽과 데이터가 많아진다면 그때에는 복제 db를 만들고 조회만 하는 것도 좋은 방법이 될것이다.
하지만 해당 프로젝트에서는 염두만 해두고 거기까진 가지 않았다.

mq를 이용하다가 cdc 를 활용하면 더욱 성능을 높일 수 있다.

데이터 필요시 mq사용법은 아래와 같다.
1. api호출로 데이터 send
2. 이중 send
   이중 send는 필요한 서비스에서 이벤트를 발행하면 이벤트를 다른 서비스에서 받아 필요한 데이터를 다시 원래 서비스로 보내는 방법이다.
   이 경우 두 서비스 모두 프로듀서와 컨슈머가 필요하다.

페이징의 경우 nooffset을 사용한다.
이때에는 쿼리 스트링을 사용해야하므로 reqeustparam 어노테이션을 활용한다.
[request param - query string]
https://kingchan223.tistory.com/58