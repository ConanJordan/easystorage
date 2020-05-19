### 关键文件说明

| 包 | 类 | 方法 | 说明 |
| - | - | - | - |
|pers.conan.database | Executable | T execute() | 执行SQL指令 |
|pers.conan.database | Command | T select(String table, Class<? extends Structure> structure) | 根据表名做全检索 |
|pers.conan.database | Command | T select(String table, String condition, Object[] args, Class<? extends Structure> structure) | 根据表名，条件，参数做检索 |
|pers.conan.database | Command |  T select(String table, String condition, String sort, Object[] args, Class<? extends Structure> structure) | 根据表明，条件，排序，参数做检索 |
|pers.conan.database | Command | T select(String sql, Object[] args, Class<? extends Structure> structure) | 根据SQL语句和参数做检索 |
|pers.conan.database | Command | T insert(String sql, Object[] args) | 根据SQL语句和参数做插入 |
|pers.conan.database | Command | T insert(String table, Structure target, Class<? extends Structure> structure) | 根据表名，目标对象做插入 |
|pers.conan.database | Command | T insert(String table, Collection<? extends Structure> targets, Class<? extends Structure> structure) | 根据表名，目标对象集合做插入 |
|pers.conan.database | Command | T update(String table, Structure target, Class<? extends Structure> structure) | 根据表名，目标对象做更新 |
|pers.conan.database | Command | T update(String table, Structure target, String condition, Object[] args, Class<? extends Structure> structure) | 根据表名，目标对象和条件做更新 |
|pers.conan.database | Command | T update(String sql, Object[] args, Class<? extends Structure> structure) | 根据SQL语句，参数做更新 |
|pers.conan.database | Command | T update(String table, Collection<? extends Structure> targets, Class<? extends Structure> structure) | 根据表名，目标对象集合做更新 |
|pers.conan.database | Command | T delete(String sql, Object[] args) | 根据SQL语句和参数做删除 |
|pers.conan.database | Command | T delete(String table, Structure target, Class<? extends Structure> structure) | 根据表名，目标对象做删除 |
|pers.conan.database | Command | T delete(String table, String condition, Object[] args) | 根据表名，条件和参数做删除 |
|pers.conan.database | Command | T delete(String table, Collection<? extends Structure> targets, Class<? extends Structure> structure) | 根据表名和目标对象集合做删除 |
| person.conan.annotation | Structure |  | 结构体接口。能转换成目标对象和查询结果集合的类都实现该接口 |
| person.conan.annotation | Column |  | 字段注解。有该注解的属性可以找到在DB中对应的字段或别名 |
| person.conan.annotation | PrimaryKey |  | 主键注解。有该注解的属性是作为主键使用。 |
| person.conan.annotation | Sequence |  | 序列号注解。有该注解的属性在DB中有可以使用的序列号(Sequence) |
| person.conan.annotation | AutoIncrement |  | 自动递增注解。有该注解的属性在插入数据时自动递增。 |