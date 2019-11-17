认证流程： 用户 通过客户端 向认证服务器申请令牌  拿到令牌之后向资源服务器申请操作 资源服务器通过客户端传递令牌去认证服务器校验
认证服务器配置：
    1 开启认证配置
            @Configuration
            @EnableAuthorizationServer
            继承springSecurity AuthorizationServerConfigurerAdapter
    2 配置信息（重写AuthorizationServerConfigurerAdapter 方法）
      配置允许哪些人可以操作  configure(AuthorizationServerEndpointsConfigurer endpoints)
      配置允许哪些客户端服务端/哪些资源服务器 可以操作  configure(ClientDetailsServiceConfigurer clients)
      配置允许校验令牌的前提  configure(AuthorizationServerSecurityConfigurer security)
    3 运行哪些人 需要实现security 接口 authenticationManager
        authenticationManager 实现在接口 WebSecurityConfigurerAdapter 中配置
        其实就是重写  WebSecurityConfigurerAdapter 中 configure(AuthenticationManagerBuilder auth) 方法
        指定用户认证逻辑 其实就是设置 加密器和UserDetailsService（查找用户信息） 的实现
        然后重写 authenticationManagerBean 方法 向spring bean中 注册 authenticationManager