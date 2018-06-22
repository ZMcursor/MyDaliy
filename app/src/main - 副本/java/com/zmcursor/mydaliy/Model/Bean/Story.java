package com.zmcursor.mydaliy.Model.Bean;

import java.util.List;

/**
 * Created by ZMcursor on 2018/5/30 0030.
 */

public class Story extends BaseStory {

    public List<String> images;

    @Override
    public String getImageUrl() {
        return images.get(0);
    }

    @Override
    public String toString() {
        return "Story{" +
                "type=" + type +
                ", id=" + id +
                ", ga_prefix=" + ga_prefix +
                ", multipic=" + multipic +
                ", title='" + title + '\'' +
                ", images=" + images +
                '}';
    }
}
//Latest{
// date='20180604',
// stories=[
// Story{type=0, id=9683296, ga_prefix=60412, multipic=false, title='大误 · 为什么霍格沃茨没有扫帚航空管制塔台？', images=[https://pic3.zhimg.com/v2-56d610671b4ba0e39f438bf2782d4232.jpg]},
// Story{type=0, id=9685261, ga_prefix=60411, multipic=false, title='腾讯和头条正面杠上了，这一次的级别和当年 3Q 大战完全不可同日而语', images=[https://pic2.zhimg.com/v2-e1ebfc51c8a33980a60df75c0ff821f9.jpg]},
// Story{type=0, id=9685087, ga_prefix=60410, multipic=false, title='成功人士每天只睡 4 小时？你只是没看到大佬们偷懒', images=[https://pic2.zhimg.com/v2-46211151ae6b95aee05edcad618e4069.jpg]},
// Story{type=0, id=9682387, ga_prefix=60409, multipic=false, title='在健身这件事上，还真是「男女有别」', images=[https://pic4.zhimg.com/v2-4d777d731124f62e1df93cd7894c7e8b.jpg]},
// Story{type=0, id=9685229, ga_prefix=60408, multipic=false, title='在德国工作，最大的感受就是深入骨子里的「秩序感」', images=[https://pic2.zhimg.com/v2-343e118175f3fae72603adc0ea9c312d.jpg]},
// Story{type=0, id=9684943, ga_prefix=60408, multipic=false, title='国内产品经理和硅谷产品经理的 3 大不同', images=[https://pic1.zhimg.com/v2-52ecf1984053543a74182068306bda58.jpg]},
// Story{type=0, id=9684823, ga_prefix=60407, multipic=false, title='在「节奏感」这件事上，你可能还比不上一只鹦鹉', images=[https://pic2.zhimg.com/v2-72f63b146ca9de510ca1dcfb7c47509d.jpg]},
// Story{type=0, id=9684905, ga_prefix=60407, multipic=false, title='腾讯的技术建设太让人糟心了', images=[https://pic2.zhimg.com/v2-419eab2ce5803f587b18cce13c85abb5.jpg]},
// Story{type=0, id=9685162, ga_prefix=60406, multipic=false, title='瞎扯 · 如何正确地吐槽', images=[https://pic3.zhimg.com/v2-41db6234997e4688d718275c9b13adee.jpg]}
// ],
// top_stories=[
// TopStory{type=0, id=9685261, ga_prefix=60411, multipic=false, title='腾讯和头条正面杠上了，这一次的级别和当年 3Q 大战完全不可同日而语', image='https://pic2.zhimg.com/v2-caaa6fddc5e03b4fd409e9387e9ef439.jpg'},
// TopStory{type=0, id=9685229, ga_prefix=60408, multipic=false, title='在德国工作，最大的感受就是深入骨子里的「秩序感」', image='https://pic1.zhimg.com/v2-9170108272b1b0433322e2529557a1e0.jpg'},
// TopStory{type=0, id=9685215, ga_prefix=60309, multipic=false, title='本周热门精选 · 世界真奇妙', image='https://pic3.zhimg.com/v2-452df7a06cd2030250eb64f9b17a979e.jpg'},
// TopStory{type=0, id=9682387, ga_prefix=60409, multipic=false, title='在健身这件事上，还真是「男女有别」', image='https://pic1.zhimg.com/v2-bc085064723d5de7a65760ba9329fdec.jpg'},
// TopStory{type=0, id=9684823, ga_prefix=60407, multipic=false, title='在「节奏感」这件事上，你可能还比不上一只鹦鹉', image='https://pic4.zhimg.com/v2-7de38d3871193852fbb3dab17bcc11bb.jpg'}
// ]
// }
