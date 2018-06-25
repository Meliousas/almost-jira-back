package io.asmame.tau.repository;

import io.asmame.tau.TauApplication;
import io.asmame.tau.config.DataConfig;
import io.asmame.tau.domain.accountManagment.entity.Role;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@MybatisTest(excludeAutoConfiguration = {AutoConfigureTestDatabase.class, SpringBootTest.class})
@SpringBootTest(classes = {TauApplication.class, DataConfig.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RoleRepositoryTests {


    @Autowired
    RoleRepository roleRepository;


    @Test
    public void CheckIfAllRolesExist(){
        List<Role> roleList = roleRepository.getAllRoles();

        assertThat(roleList).isNotEmpty();
        assertThat(roleList.size()).isEqualTo(2);
        assertThat(roleList).anyMatch(role -> role.getCode().equalsIgnoreCase("Admin"));
    }
}
