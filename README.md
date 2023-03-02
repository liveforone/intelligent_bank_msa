[kafka 사용처] - 서비스간 통신
kafka는 데이터를 조회하는 역할로써는 좋지 않다.
누군가로부터 메세지를 받아서 조회하는 용도로 쓸때에는 
feign client를 사용하자.
즉 최종 사용처가 조회(select)라면 사용하지 않는다.
kafka는 최종사용처가 추가/변경/삭제(create, update, delete)인경우에는 아주 좋다.
특히나 조인이 필요해서 read-only db를 넣고 data를 동기화 하는 상황에서는 비동기로 데이터 sync를 맞추는데에 이만한 것이 없다!!

문서화 시에 디렉토리 구조를 dto, controller로 둿다가
각 도메인 별로 뺏다가 결국 마이크로 서비스로 변경한 그 히스토리를 잘 기재하기

현재 feign의 문제점은 요청하는 http method와 같은 메소드를 가지고 있어야한다는 것이다.
post 요청 -> feign get사용 이 불가하는 것이다.
post 요청 -> feign post 사용 해야하만 가능하다.
마치 트랜잭션같다. http method가 전이가 된다.
하나의 http request안에서 feign을 호출하게되면 같은 http method로 전이가 된다. 따라서 method가 다르다면 feign이 호출되지 않는다.
이 부분이 결정적으로 feign이 가지고 있는 단점이고, 이를 잘 이용해야한다.
feign을 반드시 사용해야하는 조회 부분에서는 이를 더욱 주의해야한다.
불가피하게 알맞지 않은 http method를 사용시 반드시 문서로 남긴다.

password encoder는 서비스끼리 혼용해서 쓸수없다.
bankbook에서 만든 password는 bankbook으로 보내서 pw 판별해야한다.
이것이 싫다면 암호화 하지 않고 써야한다.

조인, 혹은 하나의 데이터를 많이 참조 하는 경우에는 read-only db를 사용했다.
통계 서비스가 이러한 방식을 사용했다.
record를 저장한 후에 record kafka는 통계 서비스로 해당 값을 전달하여 저장시킨다.
바로바로 통계서비스에 저장명령을 내림으로써 데이터를 동기화 시킨다.
kafka 커텍트를 사용하여 sync를 맞춰도 되지만, 아직은 사용하지 않고 jpa로 요청을 받을때마다 save처리 해주었다.
이렇게 동기화 시킨 데이터는 오로지 조회(read)용도로만 사용하며, 
다른 서비스에서 지속적으로 데이터를 가져오는 것이 아니라 바로바로 꺼내서 사용할 수 있어서 성능상과 전체적인 코드 구조에 큰 이점을 가져다 준다.

[향후 리팩토링 할것]
1. no offset 페이징 작업
2. feign client 성능 최적화

페이징의 경우 nooffset을 사용한다.
이때에는 쿼리 스트링을 사용해야하므로 reqeustparam 어노테이션을 활용한다.
[request param - query string]
https://kingchan223.tistory.com/58