1. Thuộc tính name trong annotation **@Entity** khác với thuộc tính name trong **@Table** như thế nào? Hãy giải thích rõ
   cần thì minh hoạ

- Thuộc tính name trong annotation **@Entity** sẽ thay đổi cả tên bảng và tên enity

```java

@Entity(name = "Class")
public class Clazz {

    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
```

--> Ở đây tên bảng và tên của entity sẽ thay đổi theo.

- Thuộc tính name trong **@Table** sẽ chỉ thay đổi tên bảng còn tên entity vẫn sẽ lấy theo mặc định.

2. Để debug câu lệnh SQL mà **Hibernate** sẽ sinh ra trong quá trình thực thi, cần phải bổ sung lệnh nào vào file **
   application.properties**?

```java
  spring.jpa.hibernate.use-new-id-generator-mappings=false
```

3. Annotation **@Column** dùng để bổ sung tính chất cho cột ứng với một thuộc tính. Tham số nào trong **@Column** sẽ đổi
   lại tên cột nếu muốn khác với tên thuộc tính, tham số nào chỉ định yêu cầu duy nhất, không được trùng lặp dữ liệu,
   tham số nào buộc trường không được null?

- Tham số dùng để thay đổi tên là  **@Column( name = "...")"**
- Tham số chỉ định yêu cầu duy nhất là **@Column( unique = true/false)"**
- Tham số chỉ định không được trùng lặp dữ liệu là **@Column( unique = true/false)"**
- Tham số chỉ định buộc trường không được null là **@Column( nullable = false)"**

4. Có 2 sự kiện mà JPA có thể bắt được, viết logic bổ sung

    - Ngay trước khi đối tượng Entity lưu xuống CSDL (ngay trước lệnh INSERT)
    - Ngay trước khi đối tượng Entity cập nhật xuống CSDL (ngay trước lệnh UPDATE)
      Vậy 2 annotation này là gì

```java
    import javax.persistence.PrePersist;
    import javax.persistence.PreUpdate;
    @PrePersist
    @PreUpdate
```

5. JpaRepository là một interface có sẵn trong thư viện JPA, nó cũng cấp các mẫu hàm thuận tiện cho thao tác dữ liệu. Cụ
   thể JpaRepository kế thừa từ interface nào?
- JpaRepository kế thừa từ 2 interface **PagingAndSortingRepository** va **QueryByExampleExecutor**

6. Hãy viết khai báo một interface repository thao tác với một Entity tên là **Post**, kiểu dữ liệu trường Identity là
   long, tuân thủ interface JpaRepository.
```java
    @Repository
    public interface PostRepository extends JpaRepository<Post, Long> {
        
    }
```

7. Khi đã chọn một cột là Identity dùng **@Id** để đánh dấu, thì có cần phải dùng xác định unique dùng annotation
   @Column(unique=true) không?
- Không cần

8. Giả sử có 1 class **Employee** với các fields sau **{id, emailAddress, firstName, lastName}**. Hãy viết các method
   trong interface EmployeeRespository để :

- Tìm tất cả các Employee theo emailAddress và lastName
```java
List<Employee> getEmployeeByEmailAddressAndLastName(String emailAddress, String lastName);
```
- Tìm tất cả các Employee khác nhau theo firstName hoặc lastName
```java
List<Employee> getDistinctByFirstNameOrLastName(String firstName, String lastName);
```
- Tìm tất cả các Employee theo lastName và sắp xếp thứ tự theo firstName tăng dần
```java
List<Employee> findByLastNameOrderByFirstNameAsc(String lastName);
```
- Tìm tất cả các Employee theo fistName không phân biệt hoa thường
```java
List<Employee> findByFirstNameIgnoreCase(String firstName);
```

9. Hãy nêu cách sử dụng của **@NamedQuery** và **@Query**. Cho ví dụ
- Cách sử dụng **@NamedQuery**: được sử dụng để định nghĩa query đơn.
```java
@NamedQuery(name = "Customer.FIND_BY_NAME", query = "SELECT c FROM Customer c WHERE c.name like :name")
```
- Cách sử dụng **@Query**: để truyền 1 câu truy vấn 
10. Làm thế nào để có thể viết custom method implemetations cho Jpa Repository. Cho ví dụ

11. Hãy nêu 1 ví dụ sử dụng **sorting** và **paging** khi query đối tượng Employee ở trên
```java
Page<Employee> page = employeeRepository.findAll(PageRequest.of(1, 5, Sort.by("firstName").descending()));
```

12. Có 3 Entity **Product.java** và **Category.java** và **Tag.java**

- Hãy bổ sung định nghĩa quan hệ **One to Many** giữa bảng**Category (One) -- Product
  (Many)**. Chú ý khi một Category xoá, thì không được phép xoá Product, mà chỉ set thuộc tính Category của Product là
  null.
- Hãy bổ sung định nghĩa quan hệ **Many to Many** giữa bảng **Tag(Many) -- Product(Many)**.

```java:Product.java
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="product")
public class Product {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;
   private String name;
}
```

```java:Category.java
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="category")
public class Category {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;
   private String name;
}
```

```java:Tag.java
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tag")
public class Tag {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;
   private String name;
```

13. Có 2 Entity **Student.java** và **Course.java**

```java:Student.java
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="student")
public class Student {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;
   private String name;
}
```

```java:Course.java
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="course")
public class Course {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;
```

Hãy mô tả quan hệ **Many to Many**:

- Một Student có thể học nhiều Course.
- Một Course có nhiều Student tham gia.
- Bảng trung gian giữa Student và Course cần có một trường là điểm score kiểu int giá trị gán vào từ 0 đến 10. Cần
  validate ở phương thức setter

Dữ liệu mẫu để kiểm thử:

student.sql

```
INSERT INTO student (id, name) VALUES (1, 'bob');
INSERT INTO student (id, name) VALUES (2, 'alice');
INSERT INTO student (id, name) VALUES (3, 'tom');
INSERT INTO student (id, name) VALUES (4, 'jane');
INSERT INTO student (id, name) VALUES (5, 'van');
INSERT INTO student (id, name) VALUES (6, 'long');
```

course.sql

```
INSERT INTO course (id, name) VALUES (1, 'math');
INSERT INTO course (id, name) VALUES (2, 'music');
INSERT INTO course (id, name) VALUES (3, 'history');
```

Nội dung môn học và điểm (Bảng trung gian)

```
bob học {math: 7, music: 5, history: 8}
alice học {math: 8, music: 2, history: 9}
tom học {math: 4, history: 10}
jane học {music: 9, history: 8}
van học {math: 9, music: 7, history: 6}
long học {math: 10, music: 3}
```

14. Với kết quả của câu 13: Hãy lập trình JPARepository và viết Unit test để tính

- Trả về liệt kê sinh viên tham gia từng môn học `Map<String, List<Student>>`: key là
  tên môn học, value là danh sách sinh viên đăng ký
- Viết Native Query để tính điểm trung bình một môn bất kỳ
- Liệt kê danh sách sinh viên học math nhưng không học music

15. Cho class `User.java` như sau

```java

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
    private String password;
}
```

Viết câu lệnh query để tìm kiếm **UserDto** bao gồm các thuộc tính **(id, name, email)** theo cách sau

- Method query + Convert to Dto
- JPQL Query
- Native Query
- Projection

16. Cho class `Post.java` như sau

```java

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "post")
public class Post {
    @Id
    private String id;
    private String title;
}
```

Viết custom generate id để tạo id ngẫu nhiên cho đối tượng post theo 2 cách

- **Cách 1** : Random ra 1 chuỗi ngẫu nhiên (UUID)
- **Cách 2** : Id được random theo cấu trúc sau `post-1`, `post-2`, ...
