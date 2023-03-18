# Intelligent Bank MSA
> 똑똑한 은행, 간편하게 사용가능한 가벼운 인터넷 은행입니다.

# 1. 프로젝트 소개
* 이 프로젝트는 [모놀리틱](https://github.com/liveforone/intelligent_bank)으로 먼저 구현된 간편 인터넷 은행 서비스 프로젝트를
* MSA로 마이그레이션 한 프로젝트 입니다.

# 2. 사용 기술 스택
* Spring Boot 3.0.3 & Spring Cloud
* Java17
* Spring Data Jpa & Query Dsl & MySql
* Spring Security & Jwt
* Junit5
* Apache Commons Lang3
* Kafka, Zookeper, Open Feign Client
* Docker & Docker-compose
* LomBok

# 3. 설명
* 비즈니스 도메인은 간편 인터넷 은행입니다.
* [프로젝트 위키](https://github.com/liveforone/intelligent_bank_msa/wiki)를 보시면 상세한 프로젝트 설계와 각 도메인별 api문서, DB설계, ERD, 상세 요구사항, 구현 기술 등이 기재되어있습니다.
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
* [Kafka Command](https://github.com/liveforone/intelligent_bank_msa/wiki/Kafka-Command)
### e. 데이터 베이스 설계
* [데이터 베이스 설계 위키](https://github.com/liveforone/intelligent_bank_msa/wiki/%EB%8D%B0%EC%9D%B4%ED%84%B0-%EB%B2%A0%EC%9D%B4%EC%8A%A4-%EC%84%A4%EA%B3%84-%EB%B0%8F-%EC%9B%90%EC%B9%99)
* [Read-Only DB](https://github.com/liveforone/intelligent_bank_msa/wiki/Read-Only-DB)
### f. Docker
* [Docker Config & Command](https://github.com/liveforone/intelligent_bank_msa/wiki/Docker-Config-&-Command)
### g. 고민한 점
* [상세한 날짜로 조회하려면 어떻게 해야할까(복잡한 조건절)?](https://github.com/liveforone/intelligent_bank_msa/wiki/%EC%83%81%EC%84%B8%ED%95%9C-%EB%82%A0%EC%A7%9C%EB%A1%9C-%EC%A1%B0%ED%9A%8C%ED%95%98%EB%A0%A4%EB%A9%B4-%EC%96%B4%EB%96%BB%EA%B2%8C-%ED%95%B4%EC%95%BC%ED%95%A0%EA%B9%8C(%EB%B3%B5%EC%9E%A1%ED%95%9C-%EC%A1%B0%EA%B1%B4%EC%A0%88)%3F)
* [다른 서비스에서 password를 판별할 수 있을까?](https://github.com/liveforone/intelligent_bank_msa/wiki/%EB%8B%A4%EB%A5%B8-%EC%84%9C%EB%B9%84%EC%8A%A4%EC%97%90%EC%84%9C-password%EB%A5%BC-%ED%8C%90%EB%B3%84%ED%95%A0-%EC%88%98-%EC%9E%88%EC%9D%84%EA%B9%8C%3F)
* [페이징 성능을 높일 순 없을까?](https://github.com/liveforone/intelligent_bank_msa/wiki/%ED%8E%98%EC%9D%B4%EC%A7%95-%EC%84%B1%EB%8A%A5%EC%9D%84-%EB%86%92%EC%9D%BC-%EC%88%9C-%EC%97%86%EC%9D%84%EA%B9%8C%3F)
### h. 리팩토링 히스토리
* [리팩토링1 - 단독 repo](https://github.com/liveforone/intelligent_bank_msa/wiki/%EB%A6%AC%ED%8C%A9%ED%86%A0%EB%A7%811---%EB%8B%A8%EB%8F%85-repo)

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
* [MSA 가이드](https://github.com/liveforone/study/blob/main/%5B%EB%82%98%EB%A7%8C%EC%9D%98%20%EC%8A%A4%ED%83%80%EC%9D%BC%20%EA%B0%80%EC%9D%B4%EB%93%9C%5D/n.%20MSA%20%EA%B0%80%EC%9D%B4%EB%93%9C.md)