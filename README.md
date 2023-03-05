# Intelligent Bank MSA
> 똑똑한 은행, 간편하게 사용가능한 가벼운 인터넷 은행입니다.

# 1. 프로젝트 소개
* 이 프로젝트는 [모놀리틱](https://github.com/liveforone/intelligent_bank)으로 먼저 구현된 간편 인터넷 은행 서비스 프로젝트를
* msa로 마이그레이션 한 프로젝트 입니다.

# 2. 사용 기술 스택
* Spring Boot 3.0.3
* Java17
* Spring Data Jpa & Query Dsl
* Spring Security & Jwt
* Apache Commons Lang3
* Kafka, Zookeper, Open Feign CLient
* MySql
* LomBok
* 이외에도 msa에 필요한 Spring Cloud 기술들이 사용되었습니다.

# 3. 설명
* 비즈니스 도메인은 간편 인터넷 은행입니다.
* [프로젝트 위키]()를 보시면 상세한 프로젝트 설계와 각 도메인별 api문서, DB설계, ERD, 상세 요구사항, 구현 기술 등이 기재되어있습니다.
* 모든 문서는 위키로 제작하였고, 링크를 달아놓았으니 클릭하셔서 보실 수 있습니다.
* 해당 프로젝트는 현재 읽고 계신 README의 5번에 기술되어있는  스타일 가이드를 지켜 제작하였습니다.
* 서버와 DB 부하를 줄이는 스케일러빌리티한 서비스을 만들려고 많이 노력하였습니다.
* 또한 비즈니스 도메인 특성상 트랜잭션이 매우 중요하여 트랜잭션 단위로 분류하여 계층을 설계하려고 노력하였습니다.
* 마지막으로 각 엔티티별로 해야하는 일들이 너무 많아지고 복잡해져서 기존의 모놀리틱 방식에서 MSA로 마이그레이션 하였습니다.

# 4. 전체 문서 위키주소
### a. 프로젝트 소개
* [프로젝트 소개]()
### b. 아키텍처 설계
* [아키텍처 설계 및 마이그레이션 이유]()
### c. 도메인별 위키
* [회원서비스]()
* [통장서비스]()
* [거래내역서비스]()
* [송금서비스]()
* [ATM서비스]()
* [정산통계서비스]()
### d. 데이터간 통신
* [데이터 통신 전략]()
* [카프카 활용 전략]()
* [Feign Client 활용 전략]()
### e. 데이터 베이스 설계
* [데이터 베이스 설계 위키]()
* [Read-Only DB]()
### f. 고민한 점
* [상세한 날짜로 조회하려면 어떻게 해야할까?(복잡한 조건절)]()
* [다른 서비스에서 password를 판별할 수 있을까?]
### g. 리팩토링 히스토리
* [리팩토링1]()
* [리팩토링2 - msa 이전]()

# 5. 스타일 가이드
* 스타일 가이드는 필자가 생각하는 좋은 코드와 필자의 클린코드 철학이 담긴 문서이다.
* 해당 프로젝트는 스타일가이드를 모두 지키며 코드를 작성했다.
* [나만의 스타일 가이드](https://github.com/liveforone/study/tree/main/%5B%EB%82%98%EB%A7%8C%EC%9D%98%20%EC%8A%A4%ED%83%80%EC%9D%BC%20%EA%B0%80%EC%9D%B4%EB%93%9C%5D)에서 전문을 읽을 수 있다.
* [가독성](https://github.com/liveforone/study/blob/main/%5B%EB%82%98%EB%A7%8C%EC%9D%98%20%EC%8A%A4%ED%83%80%EC%9D%BC%20%EA%B0%80%EC%9D%B4%EB%93%9C%5D/b.%20%EA%B0%80%EB%8F%85%EC%84%B1.md)
* [Null과 중복체크](https://github.com/liveforone/study/blob/main/%5B%EB%82%98%EB%A7%8C%EC%9D%98%20%EC%8A%A4%ED%83%80%EC%9D%BC%20%EA%B0%80%EC%9D%B4%EB%93%9C%5D/c.%20Null%EA%B3%BC%20%EC%A4%91%EB%B3%B5%20%EC%B2%B4%ED%81%AC.md)
* [분기문은 gate-way style로 하라](https://github.com/liveforone/study/blob/main/%5B%EB%82%98%EB%A7%8C%EC%9D%98%20%EC%8A%A4%ED%83%80%EC%9D%BC%20%EA%B0%80%EC%9D%B4%EB%93%9C%5D/d.%20%EB%B6%84%EA%B8%B0%EB%AC%B8%EC%9D%80%20gate-way%20%EC%8A%A4%ED%83%80%EC%9D%BC%EB%A1%9C%20%ED%95%98%EB%9D%BC.md)
* [Mapper 클래스](https://github.com/liveforone/study/blob/main/%5B%EB%82%98%EB%A7%8C%EC%9D%98%20%EC%8A%A4%ED%83%80%EC%9D%BC%20%EA%B0%80%EC%9D%B4%EB%93%9C%5D/e.%20Mapper%20%ED%81%B4%EB%9E%98%EC%8A%A4.md)
* [매직넘버를 없애라](https://github.com/liveforone/study/blob/main/%5B%EB%82%98%EB%A7%8C%EC%9D%98%20%EC%8A%A4%ED%83%80%EC%9D%BC%20%EA%B0%80%EC%9D%B4%EB%93%9C%5D/f.%20%EB%A7%A4%EC%A7%81%EB%84%98%EB%B2%84%EB%A5%BC%20%EC%97%86%EC%95%A0%EB%9D%BC.md)
* [Util 클래스](https://github.com/liveforone/study/blob/main/%5B%EB%82%98%EB%A7%8C%EC%9D%98%20%EC%8A%A4%ED%83%80%EC%9D%BC%20%EA%B0%80%EC%9D%B4%EB%93%9C%5D/g.%20Util%20%ED%81%B4%EB%9E%98%EC%8A%A4.md)
* [네이밍](https://github.com/liveforone/study/blob/main/%5B%EB%82%98%EB%A7%8C%EC%9D%98%20%EC%8A%A4%ED%83%80%EC%9D%BC%20%EA%B0%80%EC%9D%B4%EB%93%9C%5D/h.%20%EB%84%A4%EC%9D%B4%EB%B0%8D.md)
* [함수 규칙](https://github.com/liveforone/study/blob/main/%5B%EB%82%98%EB%A7%8C%EC%9D%98%20%EC%8A%A4%ED%83%80%EC%9D%BC%20%EA%B0%80%EC%9D%B4%EB%93%9C%5D/i.%20%ED%95%A8%EC%88%98.md)
* [좋은 테스트 코드](https://github.com/liveforone/study/blob/main/%5B%EB%82%98%EB%A7%8C%EC%9D%98%20%EC%8A%A4%ED%83%80%EC%9D%BC%20%EA%B0%80%EC%9D%B4%EB%93%9C%5D/j.%20%EC%A2%8B%EC%9D%80%20%ED%85%8C%EC%8A%A4%ED%8A%B8%20%EC%BD%94%EB%93%9C.md)
* [명시적 프로그래밍](https://github.com/liveforone/study/blob/main/%5B%EB%82%98%EB%A7%8C%EC%9D%98%20%EC%8A%A4%ED%83%80%EC%9D%BC%20%EA%B0%80%EC%9D%B4%EB%93%9C%5D/k.%20%EB%AA%85%EC%8B%9C%EC%A0%81%20%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D.md)
* [문서화 가이드](https://github.com/liveforone/study/blob/main/%5B%EB%82%98%EB%A7%8C%EC%9D%98%20%EC%8A%A4%ED%83%80%EC%9D%BC%20%EA%B0%80%EC%9D%B4%EB%93%9C%5D/l.%20%EB%AC%B8%EC%84%9C%ED%99%94%20%EA%B0%80%EC%9D%B4%EB%93%9C.md)
* [조건이 복잡한 쿼리에서는 컬럼을 작게 쪼개라](https://github.com/liveforone/study/blob/main/%5B%EB%82%98%EB%A7%8C%EC%9D%98%20%EC%8A%A4%ED%83%80%EC%9D%BC%20%EA%B0%80%EC%9D%B4%EB%93%9C%5D/m.%20%EC%A1%B0%EA%B1%B4%EC%9D%B4%20%EB%B3%B5%EC%9E%A1%ED%95%9C%20%EC%BF%BC%EB%A6%AC%EC%97%90%EC%84%9C%EB%8A%94%20%EC%BB%AC%EB%9F%BC%EC%9D%84%20%EC%9E%91%EA%B2%8C%20%EC%AA%BC%EA%B0%9C%EB%9D%BC.md)

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

feign과 마찬가지로 api gateway에 거는 circuit breaker도 마찬가지이다.
get, post, patch등 해당 서비스에서 쓰이는 http method에 대한 모든 fallback controller를 만들어야한다.
rest api 는 status를 전달하는 transfer이다.
이름 자체에서도 보여주듯 상태를 전달하는 규약이므로 http method라는 상태 또한 당연히 전달된다.
따라서 fallback controller를 만들때에도, feign controller를 만들때에도 호출하는 메서드 혹은 서비스에서 사용하는 http method와 동일하게 만들어야 정상 작동하는 점을 유의 하자

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

카프카 mq는 cqrs에서 c, 즉 command(명령)를 담당한다.
커맨드에만 써야한다.(반드시)
kafka와 같은 mq는 명령을 하기에, 즉 insert, update, delete와 같은 명령을 하기에 적합하다.
직접 사용을 해보니 데이터를 받아서 그 값을 꺼내서 사용에게 돌려주거나, 
또는 가공을 해서 사용자에게 돌려주거나,
마지막으로 값으로 validation check을 하는 등의 행위는 잘 되지 않고 오히려 복잡해진다.
이러한 이유로 mq는 command만 쓰자.
q 쿼리에서는 자원소모가 너무 크면 read-only db를 쓰던지, 그게 아니라면 페인클라이언트를 사용해야한다.

msa가 주는 매력은 참 크다.
그중 내가 느낀 매력은 모든 아키텍처가 그러했듯 정답은 없다는 것이다.
그 중에서도 자유도가 참 높은 아키텍처는 msa인것 같다.
예시로는 db를 서비스와 연결하다가 메세지 중심으로 변경해 메세지 큐에
모든 crud를 이야하기도 하고,
read-only db를 놓고 조회쿼리만 날리기도 하거나
혹은 서비스간 통신 시스템으로 조회를 하는 등,
서비스의 규모, 자원의 한정량 등 현재의 상황에 맞추어서 언제든지 '변경이 가능하고'
내 마음대로 또 현재 상태와 규모에 맞게끔 fit하게 튜닝이 가능하다는 점이 msa의 매력이다.
결론적으로 규모에 맞는 설계와 구현이 가능해진다는 것이다.
커지거나 작아지는 것에 대해 대응하기가 쉽다.
단순 스케일링을 넘어서 서비스 구조도, db의 구조까지 유연하게 대응하고 변경하고 대처하는 것이 가능해지는 아키텍처이다.

msa에서 집중하고 중요한 부분은 두가지인데,
첫번째는 msa로 결국에 마이그레이션한 이유는 장애처리가 크다.
서비스가 분산되고 db가 분산되다보니깐 유연하게 장애처리할 수 있고 scailability하게 설계가 가능해진다.
두번째는 데이터이다. 데이터를 가공하고 적절하게 활용하는것이 참 중요하다.
마이그레이션 한 이유라기 보다는 msa에서 단점은 데이터를 활용하는 것이기 때문이다.
이 데이터를 어떻게 가공하고, 어떻게 통신하고, 교환할지에 대해 고민하고 생각하는 것이 중요하다.

테스트에 관해서는 할말이 없다. 기존의 모놀리틱 방식에 비해 너무나도 힘들어졌다.
다른 서비스를 참조하거나 다른 서비스와 반드시 엮여 있는 서비스들을 상당히 피곤해진다.
msa의 테스트에 관해서는 더욱 고민해야겠다.

[할것]
1. [위키] 위키 이전
2. [문서] 위키 수정 및 문서 추가 + 정리
3. [문서] DB쿼리에 카피 db도 넣어서 추가 정리
4. [문서] kafka 호출 command(명령어) 정리
5. [코드] no offset 리팩터링
6. [코드] 테스트 코드 작성

페이징의 경우 nooffset을 사용한다.
이때에는 쿼리 스트링을 사용해야하므로 reqeustparam 어노테이션을 활용한다.
[request param - query string]
https://kingchan223.tistory.com/58