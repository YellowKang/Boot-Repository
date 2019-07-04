package com.kang.boot.hanlp.controller;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;
import com.kang.boot.hanlp.util.HanlpUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Api(tags = "测试")
@RequestMapping("HanlpTest")
public class HanlpTestController {

    /**
     * 查询核心地点
     * @param keyword
     * @return
     */
    @PostMapping("coreAddress")
    @ApiOperation("查询核心地点")
    public Object coreAddress(String keyword) {
        //ns为地点
        return HanlpUtil.baseCoreWord(keyword,"ns");
    }

    /**
     * 获取关键词
     * @param keyword
     * @param size
     * @return
     */
    @PostMapping("coreKeywork")
    @ApiOperation("获取关键词")
    public Object coreKeywork(String keyword,Integer size) {
        return StringUtils.isEmpty(keyword) ? null
                : size == null ? HanlpUtil.corekeyword(keyword)
                : HanlpUtil.corekeyword(keyword,size);
    }

    /**
     * 获取核心内容
     * @param keyword
     * @param size
     * @return
     */
    @PostMapping("coreContent")
    @ApiOperation("获取核心内容")
    public Object coreContent(String keyword,Integer size) {
        return StringUtils.isEmpty(keyword) ? null
                : size == null ? HanlpUtil.coreContent(keyword)
                : HanlpUtil.coreContent(keyword,size);
    }


    /**
     * 获取核心短语
     * @param keyword
     * @param size
     * @return
     */
    @PostMapping("corePhrase")
    @ApiOperation("获取核心短语")
    public Object corePhrase(String keyword,Integer size) {
        return StringUtils.isEmpty(keyword) ? null
                : size == null ? HanlpUtil.corePhrase(keyword)
                : HanlpUtil.corePhrase(keyword,size);
    }

    /**
     * 简体转换繁体
     * @param keyword
     * @return
     */
    @PostMapping("simplifiedToTraditional")
    @ApiOperation("简体转换繁体")
    public Object simplifiedToTraditional(String keyword) {
        return StringUtils.isEmpty(keyword) ? null
                : HanLP.convertToTraditionalChinese(keyword) ;
    }

    /**
     * 繁体转换简体
     * @param keyword
     * @return
     */
    @PostMapping("traditionalToSimplified")
    @ApiOperation("繁体转换简体")
    public Object traditionalToSimplified(String keyword) {
        return StringUtils.isEmpty(keyword) ? null
                : HanLP.convertToSimplifiedChinese(keyword) ;
    }

    /**
     * 机构以及公司识别
     * @param keyword
     * @return
     */
    @PostMapping("coreCompany")
    @ApiOperation("机构以及公司识别")
    public Object coreCompany(String keyword) {
        return StringUtils.isEmpty(keyword) ? null
                : HanlpUtil.baseCoreWord(keyword,"nt");
    }

}
