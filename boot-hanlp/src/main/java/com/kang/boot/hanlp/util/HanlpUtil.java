package com.kang.boot.hanlp.util;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.mining.phrase.MutualInformationEntropyPhraseExtractor;
import com.hankcs.hanlp.seg.Segment;

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
     * @param keywork
     * @param size
     * @return
     */
    public static List<String> coreKeywork(String keywork, Integer size) {
        return HanLP.extractKeyword(keywork, size);
    }

    /**
     * 重载父类方法默认统计10个核心关键字
     *
     * @param keywork
     * @return
     */
    public static List<String> coreKeywork(String keywork) {
        return coreKeywork(keywork, 10);
    }

    public static List<String> coreAddress(String keywork) {
        Segment segment = HanLP.newSegment().enablePlaceRecognize(true);
        ConcurrentSkipListSet<String> strings = new ConcurrentSkipListSet<>();
        segment.seg(keywork).stream().filter(v ->
                v.nature.toString().equals("ns")
        ).forEach(v -> {
            strings.add(v.word);
        });
        return strings.stream().collect(Collectors.toList());
    }

    /**
     * 关键内容摘要提取
     *
     * @param keywork
     * @param size
     * @return
     */
    public static List<String> coreContent(String keywork, Integer size) {
        return HanLP.extractSummary(keywork, size);
    }

    /**
     * 重载coreContent方法提取内容
     *
     * @param keywork
     * @return
     */
    public static List<String> coreContent(String keywork) {
        return coreContent(keywork, 10);
    }

    /**
     * 提取核心短语
     * @param keywork
     * @param size
     * @return
     */
    public static List<String> corePhrase(String keywork, Integer size) {
        return MutualInformationEntropyPhraseExtractor.extract(keywork, size);
    }

    /**
     * 重载提取核心短语
     * @param keywork
     * @return
     */
    public static List<String> corePhrase(String keywork) {
        return MutualInformationEntropyPhraseExtractor.extract(keywork, 10);
    }

}
