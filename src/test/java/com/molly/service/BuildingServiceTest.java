package com.molly.service;

import com.molly.config.AppConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static junit.framework.TestCase.assertTrue;

@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("unit")
public class BuildingServiceTest {
    @Test
    public void findByIdTest(){
        assertTrue(false);
    }

    @Test
    public void findByBuildingNameTest(){
        assertTrue(false);
    }

    @Test
    public void findByAreaIdTest(){
        assertTrue(false);
    }
}
