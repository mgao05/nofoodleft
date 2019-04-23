package com.molly.api;

import com.molly.domain.Area;
import com.molly.repository.AreaRepository;
import com.molly.service.AreaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@ResponseBody
@RequestMapping(value = {"api/area","api/areas"})
public class AreaController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private AreaRepository areaRepository;

    @Autowired
    private AreaService areaService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Area> getAreaList(){
        logger.debug("List area");
        return areaRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET,params = {"areaName"})
    public Area generateArea(@RequestParam("areaName") String areaName){
        logger.debug(areaName);
        return areaService.findByAreaName(areaName);}
}
