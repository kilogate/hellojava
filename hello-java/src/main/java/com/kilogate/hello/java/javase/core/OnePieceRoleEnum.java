package com.kilogate.hello.java.javase.core;

/**
 * 海贼王角色枚举
 *
 * @author fengquanwei
 * @create 2017/6/13 19:45
 **/
public enum OnePieceRoleEnum {
    LUFFY("草帽", "モンキーD·ルフィ", "蒙其·D·路飞", "Monkey D. Luffy"),
    ZORO("海贼猎人", "ロロノア·ゾロ", "罗罗诺亚·索隆", "Roronoa Zoro"),
    NAMI("小贼猫", "ナミ", "娜美", "Nami"),
    USOPP("GOD", "ウソップ", "乌索普", "Usopp"),
    SANJI("黑足", "サンジ", "山治", "Sanji"),
    CHOPPER("爱吃棉花糖的驯鹿", "トニートニーチョッパー", "托尼托尼·乔巴", "Tony Tony Chopper"),
    ROBIN("恶魔之子", "ニコ·ロビン", "妮可·罗宾", "Nico Robin"),
    FRANKY("改造人", "フランキー", "弗兰奇", "Franky"),
    BROOK("灵魂之王", "ブルック", "布鲁克", "Brook");

    private String nickName; // 绰号
    private String japaneseName;
    private String chineseName;
    private String englishhName;

    OnePieceRoleEnum(String nickName, String japaneseName, String chineseName, String englishhName) {
        this.nickName = nickName;
        this.japaneseName = japaneseName;
        this.chineseName = chineseName;
        this.englishhName = englishhName;
    }

    public static void main(String[] args) {
        System.out.println(OnePieceRoleEnum.LUFFY.toString());
        OnePieceRoleEnum luffy = Enum.valueOf(OnePieceRoleEnum.class, "LUFFY");
        System.out.println(OnePieceRoleEnum.LUFFY.ordinal());
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getJapaneseName() {
        return japaneseName;
    }

    public void setJapaneseName(String japaneseName) {
        this.japaneseName = japaneseName;
    }

    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }

    public String getEnglishhName() {
        return englishhName;
    }

    public void setEnglishhName(String englishhName) {
        this.englishhName = englishhName;
    }
}
