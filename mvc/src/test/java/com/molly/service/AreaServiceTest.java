package com.molly.service;

import com.molly.config.AppConfig;
import com.molly.domain.Area;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.molly.service.AreaService;

import javax.transaction.Transactional;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("unit")
public class AreaServiceTest {
    @Autowired
    private AreaService areaService;
    private Area newArea;

    @Before
    public void setup(){
        newArea = new Area();
        newArea.setAreaName("bikeshop");

    }

    @Test
    @Transactional
    public void findByIdTest(){
        areaService.save(newArea);
       Area testArea = areaService.findById(newArea.getId());
       assertNotNull(testArea);
       assertEquals(newArea.getId(),testArea.getId());
    }

    @Test
    @Transactional
    public void findByAreaNameTest(){
        areaService.save(newArea);
        Area testArea = areaService.findByAreaName(newArea.getAreaName());
        assertNotNull(testArea);
        assertEquals(newArea.getAreaName(),testArea.getAreaName());

    }
}
