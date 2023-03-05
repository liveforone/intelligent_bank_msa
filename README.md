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
* [프로젝트 소개](https://github.com/liveforone/intelligent_bank_msa/wiki/%EB%93%A4%EC%96%B4%EA%B0%80%EB%A9%B0)
### b. 아키텍처 설계
* [아키텍처 설계 및 마이그레이션 이유](https://github.com/liveforone/intelligent_bank_msa/wiki/%EC%95%84%ED%82%A4%ED%85%8D%EC%B2%98-%EC%84%A4%EA%B3%84-%EB%B0%8F-%EB%A7%88%EC%9D%B4%EA%B7%B8%EB%A0%88%EC%9D%B4%EC%85%98-%EC%9D%B4%EC%9C%A0)
### c. 도메인별 위키
* [회원 서비스](https://github.com/liveforone/intelligent_bank_msa/wiki/%ED%9A%8C%EC%9B%90%EC%8B%9C%EC%8A%A4%ED%85%9C)
* [통장 서비스](https://github.com/liveforone/intelligent_bank_msa/wiki/%ED%86%B5%EC%9E%A5%EC%84%9C%EB%B9%84%EC%8A%A4)
* [거래내역 서비스](https://github.com/liveforone/intelligent_bank_msa/wiki/%EA%B1%B0%EB%9E%98%EB%82%B4%EC%97%AD-%EC%84%9C%EB%B9%84%EC%8A%A4)
* [송금 서비스](https://github.com/liveforone/intelligent_bank_msa/wiki/%EC%86%A1%EA%B8%88-%EC%84%9C%EB%B9%84%EC%8A%A4)
* [ATM 서비스](https://github.com/liveforone/intelligent_bank_msa/wiki/ATM-%EC%84%9C%EB%B9%84%EC%8A%A4)
* [정산통계 서비스](https://github.com/liveforone/intelligent_bank_msa/wiki/%EC%A0%95%EC%82%B0-%ED%86%B5%EA%B3%84-%EC%84%9C%EB%B9%84%EC%8A%A4)
### d. 데이터간 통신
* [데이터 통신 전략](https://github.com/liveforone/intelligent_bank_msa/wiki/%EB%8D%B0%EC%9D%B4%ED%84%B0-%ED%86%B5%EC%8B%A0-%EC%A0%84%EB%9E%B5)
* [카프카 활용 전략](https://github.com/liveforone/intelligent_bank_msa/wiki/%EC%B9%B4%ED%94%84%EC%B9%B4-%ED%99%9C%EC%9A%A9-%EC%A0%84%EB%9E%B5)
* [Feign Client 활용 전략](https://github.com/liveforone/intelligent_bank_msa/wiki/Feign-Client-%ED%99%9C%EC%9A%A9-%EC%A0%84%EB%9E%B5)
### e. 데이터 베이스 설계
* [데이터 베이스 설계 위키](https://github.com/liveforone/intelligent_bank_msa/wiki/%EB%8D%B0%EC%9D%B4%ED%84%B0-%EB%B2%A0%EC%9D%B4%EC%8A%A4-%EC%84%A4%EA%B3%84-%EB%B0%8F-%EC%9B%90%EC%B9%99)
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

[인덱스]
통장 서비스에 email에도 인덱스 추가





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





msa에서 집중하고 중요한 부분은 두가지인데,
첫번째는 msa로 결국에 마이그레이션한 이유는 장애처리가 크다.
서비스가 분산되고 db가 분산되다보니깐 유연하게 장애처리할 수 있고 scailability하게 설계가 가능해진다.
두번째는 데이터이다. 데이터를 가공하고 적절하게 활용하는것이 참 중요하다.
마이그레이션 한 이유라기 보다는 msa에서 단점은 데이터를 활용하는 것이기 때문이다.
이 데이터를 어떻게 가공하고, 어떻게 통신하고, 교환할지에 대해 고민하고 생각하는 것이 중요하다.

테스트에 관해서는 할말이 없다. 기존의 모놀리틱 방식에 비해 너무나도 힘들어졌다.
다른 서비스를 참조하거나 다른 서비스와 반드시 엮여 있는 서비스들을 상당히 피곤해진다.
msa의 테스트에 관해서는 더욱 고민해야겠다.

msa로 제작시 주의 점은 4가지
fallback controller (http method 전이 : rest의 특성)
feign controller (http method 전이 : rest의 특성)
kafka
circuit breaker

[할것]
1. [위키] 위키 이전
2. [문서] 위키 수정 및 문서 추가 + 정리
3. [문서] DB쿼리에 카피 db도 넣어서 추가 정리
4. [문서] kafka 호출 command(명령어) 정리
5. [코드] no offset 리팩터링
6. [코드] 테스트 코드 작성

페이징의 경우 no offset을 사용한다.
이때에는 쿼리 스트링을 사용해야하므로 reqeustparam 어노테이션을 활용한다.
[request param - query string]
https://kingchan223.tistory.com/58