package com.lzqs.zhangyushu.Controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lzqs.zhangyushu.common.ResultInfo;
import com.lzqs.zhangyushu.config.Action;
import com.lzqs.zhangyushu.entity.Clew;
import com.lzqs.zhangyushu.entity.Organization;
import com.lzqs.zhangyushu.entity.SampleReels;
import com.lzqs.zhangyushu.entity.User;
import com.lzqs.zhangyushu.service.*;
import org.apache.velocity.runtime.directive.Foreach;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/organization")
public class OrganizationController {
    @Resource
    UserService userService;
    @Resource
    CommonFileService commonFileService;
    @Resource
    OrganizationService organizationService;
    @Resource
    ClewService clewService;
    @Resource
    Action action;
    @Resource
    SampleReelsService sampleReelsService;

    @PostMapping("/organizationDetail")
    @ResponseBody
    public ResultInfo organizationDetail(@RequestBody Map<String, Object> map, HttpServletRequest request) {
        String checkToken = action.checkToken(request);
        if (checkToken == null) {
            Long organizationId = Long.valueOf(map.get("organizationId").toString());
            Map<String, Object> allInfo = new HashMap<>();
            Organization organization = organizationService.getById(organizationId);
            allInfo.put("机构名", organization.getOrganizationName());
            allInfo.put("logo", commonFileService.getById(organization.getOrganizationLogo()).getFilePath());
            allInfo.put("我的学员",!userService.getList(organizationId,3).isEmpty()? userService.getList(organizationId,3).size() : "无学生");
            allInfo.put("作品集数", sampleReelsService.getNumber(organizationId).get("size"));
            allInfo.put("家长转发", sampleReelsService.getNumber(organizationId).get("forward"));
            allInfo.put("累计访问", sampleReelsService.getNumber(organizationId).get("view"));
            allInfo.put("招生线索", clewService.getNumber(organizationId, 0).get("viewSize"));
            allInfo.put("获取招生线素", clewService.getNumber(organizationId, 0).get("allSize"));
            allInfo.put("绘馆封面", sampleReelsService.getGallery(organizationId));

            return ResultInfo.success().add(allInfo);
        }
        return ResultInfo.failWithMsg(checkToken);
    }

    //机构 - 我的学员
    @PostMapping("/getStudent")
    @ResponseBody
    public ResultInfo inviteStudent(@RequestBody Map<String, Object> map, HttpServletRequest request) {
        String checkToken = action.checkToken(request);
        if (checkToken == null) {
            Long organizationId = Long.valueOf(map.get("organizationId").toString());
            List<User> studentList = userService.list(new QueryWrapper<User>().eq("binding_id", organizationId).eq("status", 3));
            List studentListInfo = new ArrayList();
            if (studentList.isEmpty()) {
                return ResultInfo.success().add("暂无学员，邀请学员加入我们机构吧");
            }
            int allCommentNumb = 0, allLikeNumb = 0;
            for (User user : studentList) {
                List<SampleReels> sampleReels = sampleReelsService.list(new QueryWrapper<SampleReels>().eq("user_id", user.getUserId()).eq("organization_id", organizationId));
                Map<String, Object> studentsInfo = new HashMap<>();
                studentsInfo.put("学员名称", user.getUserName());
                studentsInfo.put("作品集", sampleReels.size());
                int likeNumber = 0, commentNumber = 0;
                for (SampleReels sampleReels1 : sampleReels) {
                    likeNumber += sampleReels1.getLikeNum();
                    allLikeNumb += sampleReels1.getLikeNum();
                    commentNumber += sampleReels1.getCommentNum();
                    allCommentNumb += sampleReels1.getCommentNum();
                }
                studentsInfo.put("评论", commentNumber);
                studentsInfo.put("赞", likeNumber);

                studentListInfo.add(studentsInfo);
            }

            Map<String, Object> allInfo = new HashMap<>();
            allInfo.put("机构名称", organizationService.getById(organizationId).getOrganizationName());
            allInfo.put("总评论", allCommentNumb);
            allInfo.put("总赞数", allLikeNumb);
            allInfo.put("所有学员信息", studentListInfo);

            return ResultInfo.success().add(allInfo);
        }
        return ResultInfo.failWithMsg(checkToken);
    }

    //招生线索
    @PostMapping("/organizationRegistrationIndex")
    @ResponseBody
    public ResultInfo organizationRegistrationIndex(@RequestBody Map<String, Object> map, HttpServletRequest request) {

        String checkToken = action.checkToken(request);
        if (checkToken == null) {
            Long organizationId = Long.valueOf(map.get("organizationId").toString());
            List<Clew> clews = clewService.list(new QueryWrapper<Clew>().eq("organization_id", organizationId));
            Map<String, List> clewsMap = new HashMap<>();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日HH:mm:ss");
            for (Clew clew : clews) {
                LocalDate time = clew.getCreateTime().toLocalDate();
                Map<String, Object> studentDetail = new HashMap<>();
                studentDetail.put("学员姓名", clew.getStudentName());
                studentDetail.put("最后跟进时间", clew.getEditTime().format(formatter));
                List list = clewsMap.containsKey(time.toString()) ? clewsMap.get(time.toString()) : new ArrayList();
                studentDetail.put("numb", list.size() + 1);
                list.add(studentDetail);

                clewsMap.put(time.toString(), list);
            }
            return ResultInfo.success().add(clewsMap);
        }
        return ResultInfo.failWithMsg(checkToken);
    }

    //线索详情
    @PostMapping("/registrationIndexDetail")
    @ResponseBody
    public ResultInfo registrationIndexDetail(@RequestBody Map<String, Object> map, HttpServletRequest request) {
        String checkToken = action.checkToken(request);
        if (checkToken == null) {
            Long clewId = Long.valueOf(map.get("clewId").toString());
            Clew clew = clewService.getById(clewId);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日HH:mm:ss");
            Map<String, Object> clewDetail = new HashMap<>();
            clewDetail.put("学生名称", clew.getStudentName());
            clewDetail.put("线索产生时间", clew.getCreateTime().format(formatter));
            clewDetail.put("备注信息", clew.getRemark());
            clewDetail.put("家长电话", clew.getParentPhone());
            clewDetail.put("跟进次数", clew.getFollowUpNum());
            clewDetail.put("最后跟进时间", clew.getEditTime().format(formatter));
            return ResultInfo.success().add(clewDetail);
        }
        return ResultInfo.failWithMsg(checkToken);
    }

    //机构-设置
    @PostMapping("/setting")
    @ResponseBody
    public ResultInfo setting(@RequestBody Map<String, Object> map, HttpServletRequest request) {
        String checkToken = action.checkToken(request);
        if (checkToken == null) {
            Long organizationId = Long.valueOf(map.get("organizationId").toString());
            Organization organization = organizationService.getById(organizationId);
            Map<String, Object> detail = new HashMap<>();
            detail.put("机构名", organization.getOrganizationName());
            detail.put("手机号", organization.getUserPhone());
            detail.put("logo", commonFileService.getById(organization.getOrganizationLogo()).getFilePath());
            detail.put("管理员", !userService.getList(organizationId,4).isEmpty()? userService.getList(organizationId,4).size() : "无管理员");
            return ResultInfo.success().add(detail);
        }
        return ResultInfo.failWithMsg(checkToken);
    }

    //添加管理员
    @PostMapping("/addOrganizer")
    @ResponseBody
    public ResultInfo addOrganizer(@RequestBody Map<String, Object> map, HttpServletRequest request) {
        String checkToken = action.checkToken(request);
        if (checkToken == null) {
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

    //学员详情
    @PostMapping("/studentDetail")
    @ResponseBody
    public ResultInfo studentDetail(@RequestBody Map<String, Object> map, HttpServletRequest request) {
        String checkToken = action.checkToken(request);
        if (checkToken == null) {
            Long studentId = Long.valueOf(map.get("userId").toString());
            Long organizationId = Long.valueOf(map.get("organizationId").toString());
            List<SampleReels> sampleReels = sampleReelsService.list(new QueryWrapper<SampleReels>().eq("organization_id",organizationId )
            .eq("user_id", studentId));
            List collectionList = new ArrayList();
            for (SampleReels sampleReels1: sampleReels){
                Map<String, Object> collection = new HashMap<>();
                collection.put("封面", commonFileService.getById(sampleReels1.getSampleReelsCover()).getFilePath());
                collection.put("作品集名称", sampleReels1.getSampleReelsName());
                collection.put("作品集描述", sampleReels1.getSamoleReelsDesc());
                collection.put("赞数", sampleReels1.getLikeNum());
                collection.put("评论数", sampleReels1.getCommentNum());

                collectionList.add(collection);
            }
            Map<String, Object> allInfo = new HashMap<>();
            User student = userService.getById(studentId);
            allInfo.put("学员头像", student.getUserHead());
            allInfo.put("学员名称", student.getUserName());
            allInfo.put("作品集表", !collectionList.isEmpty()? collectionList:"无作品集");

            return ResultInfo.success().add(allInfo);
        }
        return ResultInfo.failWithMsg(checkToken);
    }
}
