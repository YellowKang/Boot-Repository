package com.kang.boot.hanlp.util;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.mining.phrase.MutualInformationEntropyPhraseExtractor;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.stream.Collectors;

/**
 * @Author BigKang
 * @Date 2019/6/28 16:47
 * @Summarize Hanlp工具类
 */
public class HanlpUtil {
    /**
     * 热点词统计。size为返回的热点词个数
     *
     * @param keyword
     * @param size
     * @return
     */
    public static List<String> corekeyword(String keyword, Integer size) {
        return HanLP.extractKeyword(keyword, size);
    }

    /**
     * 重载父类方法默认统计10个核心关键字
     *
     * @param keyword
     * @return
     */
    public static List<String> corekeyword(String keyword) {
        return corekeyword(keyword, 10);
    }

    public static List<String> baseCoreWord(String keyword,String type) {
        Segment segment = HanLP.newSegment().enablePlaceRecognize(true);
        ConcurrentSkipListSet<String> strings = new ConcurrentSkipListSet<>();
        segment.seg(keyword).stream().filter(v ->
                v.nature.toString().equals(type)
        ).forEach(v -> {
            strings.add(v.word);
        });
        return strings.stream().collect(Collectors.toList());
    }

    /**
     * 关键内容摘要提取
     *
     * @param keyword
     * @param size
     * @return
     */
    public static List<String> coreContent(String keyword, Integer size) {
        return HanLP.extractSummary(keyword, size);
    }

    /**
     * 重载coreContent方法提取内容
     *
     * @param keyword
     * @return
     */
    public static List<String> coreContent(String keyword) {
        return coreContent(keyword, 10);
    }

    /**
     * 提取核心短语
     * @param keyword
     * @param size
     * @return
     */
    public static List<String> corePhrase(String keyword, Integer size) {
        return MutualInformationEntropyPhraseExtractor.extract(keyword, size);
    }

    /**
     * 重载提取核心短语
     * @param keyword
     * @return
     */
    public static List<String> corePhrase(String keyword) {
        return MutualInformationEntropyPhraseExtractor.extract(keyword, 10);
    }

}
