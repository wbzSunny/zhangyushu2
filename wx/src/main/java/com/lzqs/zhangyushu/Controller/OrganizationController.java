package com.lzqs.zhangyushu.Controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lzqs.zhangyushu.common.ResultInfo;
import com.lzqs.zhangyushu.config.Action;
import com.lzqs.zhangyushu.entity.Clew;
import com.lzqs.zhangyushu.entity.Organization;
import com.lzqs.zhangyushu.entity.SampleReels;
import com.lzqs.zhangyushu.entity.User;
import com.lzqs.zhangyushu.paramUtil.ParamCheckUtils;
import com.lzqs.zhangyushu.paramUtil.ParamTransformationUtils;
import com.lzqs.zhangyushu.safety.JwtUtil;
import com.lzqs.zhangyushu.service.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/organization")
public class OrganizationController {

    @Resource
    UserService userService;
    @Resource
    CommonFileService commonFileService;
    OrganizationService organizationService;
    @Resource
    ClewService clewService;
    @Resource
    Action action;
    @Resource
    SampleReelsService sampleReelsService ;


    /**
     * 创建机构
     * @param request
     * @param map
     * @return
     */
    @PostMapping("/addOrganization")
    public ResultInfo addOrganization(HttpServletRequest request, @RequestBody Map<String, Object> map){
        String token = request.getHeader("token");
        if (token == null){
            return ResultInfo.failWithMsg("token 空");
        }
        String userId  = JwtUtil.getUserId(token);
        if ( userId == null){
            return  ResultInfo.failWithMsg("token 错误");
        }

        String organizationName = ParamTransformationUtils.transformToString(map.get("organizationName"));
        String linkMan = ParamTransformationUtils.transformToString(map.get("linkMan"));
        String mobile = ParamTransformationUtils.transformToString(map.get("mobile"));
        String organizationdesc = ParamTransformationUtils.transformToString(map.get("organizationdesc"));
        if ( ParamCheckUtils.paramIsNull(linkMan,organizationdesc,organizationName,mobile)){
            return ResultInfo.failWithMsg("参数不正确");
        }
        return organizationService.addOrganization(request,userId,linkMan,mobile,organizationName,organizationdesc);
    }

    @PostMapping("/organizationDetail") //// 未完成
    @ResponseBody
    public ResultInfo organizationDetail(@RequestBody Map<String, Object> map, HttpServletRequest request){
        String checkToken = action.checkToken(request);
        if (checkToken==null){
            Long organizationId = Long.valueOf(map.get("organizationId").toString());

            Map<String, Object> allInfo = new HashMap<>();
            Organization organization = organizationService.getById(organizationId);
            allInfo.put("机构名", organization.getOrganizationName());
            allInfo.put("logo", commonFileService.getById(organization.getOrganizationLogo()).getFilePath());
            List<Clew> clews = clewService.list(new QueryWrapper<Clew>().eq("organization_id", organizationId).eq("view_num", 0));
            allInfo.put("招生线索", clews.size());
            List<SampleReels> sampleReels = sampleReelsService.list(new QueryWrapper<SampleReels>().eq("organization_id", organizationId));
            allInfo.put("作品集", sampleReels.size());

            //// 未完成
            return  ResultInfo.success().add(allInfo);
        }
        return ResultInfo.failWithMsg(checkToken);
    }

    //招生线索
    @PostMapping("/organizationRegistrationIndex")
    @ResponseBody
    public ResultInfo organizationRegistrationIndex(@RequestBody Map<String, Object> map, HttpServletRequest request){

//        String checkToken = action.checkToken(request);
        String checkToken =null;
        if (checkToken==null){
            Long organizationId = Long.valueOf(map.get("organizationId").toString());
            List<Clew> clews = clewService.list(new QueryWrapper<Clew>().eq("organization_id", organizationId));
            Map<String, List> clewsMap = new HashMap<>();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日HH:mm:ss");
            for (Clew clew : clews){
                LocalDate time = clew.getCreateTime().toLocalDate();
                Map<String, Object> studentDetail = new HashMap<>();
                studentDetail.put("学员姓名", clew.getStudentName());
                studentDetail.put("最后跟进时间", clew.getEditTime().format(formatter));
                List list = clewsMap.containsKey(time.toString())? clewsMap.get(time.toString()) : new ArrayList();
                studentDetail.put("numb", list.size()+1);
//                int size= list.size();
//                list.add(size+1);
//                list.add(list.size());
                list.add(studentDetail);
//                number.add(list.size());

                clewsMap.put(time.toString(), list);
            }
            return ResultInfo.success().add(clewsMap);
        }
        return ResultInfo.failWithMsg(checkToken);
    }

    //线索详情
    @PostMapping("/registrationIndexDetail")
    @ResponseBody
    public ResultInfo registrationIndexDetail(@RequestBody Map<String, Object> map, HttpServletRequest request){
        String checkToken = action.checkToken(request);
        if (checkToken==null){
            Long clewId = Long.valueOf(map.get("clewId").toString());
            Clew clew = clewService.getById(clewId);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd HH:mm:ss");
            Map<String, Object> clewDetail = new HashMap<>();
            clewDetail.put("学生名称", clew.getStudentName());
            clewDetail.put("线索产生时间", clew.getCreateTime().format(formatter));
            clewDetail.put("备注信息", clew.getRemark());
            clewDetail.put("备注信息", clew.getParentPhone());
            clewDetail.put("备注信息", clew.getFollowUpNum());
            clewDetail.put("最后跟进时间", clew.getEditTime());
            return  ResultInfo.success().add(clewDetail);
        }
        return ResultInfo.failWithMsg(checkToken);
    }

    //设置
    @PostMapping("/setting")
    @ResponseBody
    public ResultInfo setting(@RequestBody Map<String, Object> map, HttpServletRequest request){
        String checkToken = action.checkToken(request);
        if (checkToken==null){
            Long organizationId = Long.valueOf(map.get("organizationId").toString());
            Organization organization = organizationService.getById(organizationId);
            Map<String, Object> detail = new HashMap<>();
            detail.put("机构名", organization.getOrganizationName());
            detail.put("联系人电话", organization.getUserPhone());
            detail.put("logo", commonFileService.getById(organization.getOrganizationLogo()).getFilePath());
            return ResultInfo.success().add(detail);
        }
        return ResultInfo.failWithMsg(checkToken);
    }

    //添加管理员
    @PostMapping("/addOrganizer")
    @ResponseBody
    public ResultInfo addOrganizer(@RequestBody Map<String, Object> map, HttpServletRequest request){
        String checkToken = action.checkToken(request);
        if (checkToken==null){
            Long organizationId = Long.valueOf(map.get("organizationId").toString());
            Long userId = Long.valueOf(map.get("userId").toString());
            User user = userService.getById(userId);
            user.setStatus(4);
            user.setBindingId(organizationId);
            userService.updateById(user);
            return ResultInfo.success();
        }
        return ResultInfo.failWithMsg(checkToken);
    }



}

//dffdfdfdf