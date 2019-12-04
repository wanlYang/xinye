package com.topshow.controller;

import com.topshow.entity.Result;
import com.topshow.entity.Shop;
import com.topshow.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 店面管理控制器
 */
@Controller
@RequestMapping("/admin/shop")
public class BackStageShopController {

    @Autowired
    private ShopService shopService;

    /**
     * 获取店面图列表
     * @return
     */
    @RequestMapping(value = "/list/get",method = RequestMethod.POST)
    @ResponseBody
    public Result shopList(){
        List<Shop> shops = shopService.getAllShop();
        Result result = new Result();
        result.setStatus(200);
        result.setMessage("获取成功!");
        result.setCount(shops.size());
        result.setData(shops);
        return result;
    }

    @RequestMapping(value = "/add/submit",method = RequestMethod.POST)
    @ResponseBody
    public Result addShop(Shop shop){
        Integer row = shopService.insert(shop);
        Result result = new Result();
        result.setStatus(200);
        result.setMessage("添加成功!");
        result.setCount(row);
        result.setData(row);
        return result;
    }

    @RequestMapping(value = "/edit/submit",method = RequestMethod.POST)
    @ResponseBody
    public Result editShop(Shop shop){
        Integer row = shopService.update(shop);
        Result result = new Result();
        result.setStatus(200);
        result.setMessage("编辑成功!");
        result.setCount(row);
        result.setData(row);
        return result;
    }
    @RequestMapping(value = "/delete/submit",method = RequestMethod.POST)
    @ResponseBody
    public Result delete(String id){
        Integer row = shopService.delete(id);
        Result result = new Result();
        result.setStatus(200);
        result.setMessage("删除成功!");
        result.setCount(row);
        result.setData(row);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = { "/upload/img" }, method = { RequestMethod.POST })
    public Map<String, Object> changeImg(HttpServletRequest request, @RequestParam("file") MultipartFile file, String flag, HttpSession session)
            throws IllegalStateException, IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
        String rootPath = request.getServletContext().getRealPath("/admin/uploads");
        String res = sdf.format(new Date());
        String originalFilename = file.getOriginalFilename();
        String newFileName = res + originalFilename.substring(originalFilename.lastIndexOf("."));
        File newFile = new File(rootPath + File.separator + "shop" + File.separator + newFileName);
        if (!newFile.getParentFile().exists()) {
            newFile.getParentFile().mkdirs();
        }
        file.transferTo(newFile);
        String fileUrl = "/admin/uploads/shop/" + newFileName;

        Map<String, Object> map = new HashMap<String, Object>();

        map.put("code", Integer.valueOf(0));
        map.put("msg", "上传成功!");
        Map<String, Object> mapData = new HashMap<String, Object>();
        mapData.put("src", request.getContextPath() + fileUrl);
        mapData.put("title", newFileName);
        map.put("data", mapData);
        return map;
    }
}
