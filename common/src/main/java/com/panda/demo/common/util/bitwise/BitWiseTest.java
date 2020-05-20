package com.panda.demo.common.util.bitwise;

/**
 * @author huixiangdou
 * @date 2020/5/9
 */
public class BitWiseTest {
    private int statusFlag;

    public BitWiseTest(int statusFlag) {
        this.statusFlag = statusFlag;
    }

    /**
     * 获取二进制状态位的某一位代表的值
     *
     * @param position 第几位 从右至左 从1开始
     * @return 0/1
     */
    public int getStatusByPosition(int position) {
        if (this.statusFlag < 0 || position < 1 || position > 31) {
            return 0;
        }
        return ((this.statusFlag >> (position - 1)) & 1);
    }


    /**
     * 将第几位的状态设为0/1
     *
     * @param position 第几位 从右至左 从1开始
     * @param status   0/1
     */
    public void setStatusInPosition(int position, int status) {
        if (statusFlag < 0) {
            statusFlag = 0;
        }

        if (position < 1 || position > 31) {
            return;
        }

        if (status == 0) {
            this.statusFlag = (statusFlag & ~(1 << (position - 1)));
        } else if (status == 1) {
            this.statusFlag = (statusFlag | (1 << (position - 1)));
        }
    }

    public int getStatusFlag() {
        return statusFlag;
    }

    public static void main(String[] args) {
        BitWiseTest bisWise = new BitWiseTest(7);
        bisWise.setStatusInPosition(2,0);
        System.out.println(bisWise.getStatusFlag());


        System.out.println(1<<2);
        System.out.println(1<<3);
        System.out.println(1<<4);
    }
}
