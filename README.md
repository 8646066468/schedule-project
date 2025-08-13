# 일정 관리 앱 (Schedule Management App)

## 프로젝트 소개
본 프로젝트는 Spring Boot와 JPA를 활용한 **간단한 일정 관리 애플리케이션**입니다.  
사용자는 회원가입 및 로그인을 통해 개인 일정을 생성, 조회, 수정, 삭제할 수 있습니다.  

- **언어/프레임워크**: Java, Spring Boot, JPA, Hibernate  
- **데이터베이스**: MySQL (또는 H2 테스트 가능)  
- **인증 방식**: 세션 기반 로그인  

---

## 기능

### 사용자(User) 기능
| 기능 | Method | Endpoint | 설명 |
|------|--------|----------|-----|
| 회원가입 | POST | `/signup` | 이름, 이메일, 비밀번호로 회원 가입 |
| 로그인 | POST | `/login` | 이메일, 비밀번호로 로그인 (세션 생성) |
| 로그아웃 | POST | `/logout` | 세션 종료 |
| 전체 사용자 조회 | GET | `/users` | 모든 사용자 정보 조회 |
| 단일 사용자 조회 | GET | `/users/{id}` | 특정 사용자 정보 조회 |
| 사용자 정보 수정 | PUT | `/users` | 로그인 사용자 정보 수정 |
| 사용자 삭제 | DELETE | `/users` | 로그인 사용자 삭제 |

### 일정(Schedule) 기능
| 기능 | Method | Endpoint | 설명 | 인증 필요 |
|------|--------|----------|-----|-----|
| 일정 생성 | POST | `/users/{userId}/schedules` | 로그인 사용자 일정 생성 | ✅ |
| 일정 조회 | GET | `/users/{userId}/schedules` | 로그인 사용자 전체 일정 조회 | ✅ |
| 일정 단일 조회 | GET | `/users/{userId}/schedules/{scheduleId}` | 특정 일정 조회 | ✅ |
| 일정 수정 | PUT | `/users/{userId}/schedules/{scheduleId}` | 특정 일정 수정 | ✅ |
| 일정 삭제 | DELETE | `/users/{userId}/schedules/{scheduleId}` | 특정 일정 삭제 | ✅ |


### 데이터베이스 테이블
##  ERD (Entity Relationship Diagram)

### Entity

#### User (사용자)
| 컬럼명 | 타입 | 제약조건 | 설명 |
|--------|------|---------|-----|
| id | Long | PK, Auto Increment | 사용자 고유 ID |
| name | String | NOT NULL | 사용자 이름 |
| email | String | NOT NULL, UNIQUE | 사용자 이메일 |
| password | String | NOT NULL | 비밀번호 (암호화 저장) |
| createdAt | LocalDateTime | 자동생성 | 생성 시간 |
| modifiedAt | LocalDateTime | 자동갱신 | 수정 시간 |

#### Schedule (일정)
| 컬럼명 | 타입 | 제약조건 | 설명 |
|--------|------|---------|-----|
| id | Long | PK, Auto Increment | 일정 고유 ID |
| title | String | NOT NULL, 10자 제한 | 일정 제목 |
| content | String | NOT NULL | 일정 내용 |
| createdAt | LocalDateTime | 자동생성 | 생성 시간 |
| modifiedAt | LocalDateTime | 자동갱신 | 수정 시간 |
| user_id | Long | FK (User.id) | 작성자 ID |

---

### 관계 (Relationship)

- **User : Schedule = 1 : N**  
  한 명의 사용자는 여러 일정을 작성할 수 있음

---

### ERD 시각화 예시
<img width="792" height="446" alt="image" src="https://github.com/user-attachments/assets/15a76364-4b6e-4440-b430-42ae01d7c991" />

