package com.magenta.task.simulation.util;

import com.magenta.task.simulation.dataMock.ScheduleData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

class CalcTimeUtilTest {

    @Test
    void calcDrivingTime() {
        int actDrivingTime = CalcTimeUtil.calcDrivingTime(ScheduleData.UAZ_452, ScheduleData.POINT1, ScheduleData.DC);
        int expDrivingTime = ScheduleData.DRIVING_TIME_DC_POINT1;
        Assertions.assertEquals(expDrivingTime, actDrivingTime);
    }

    @Test
    void calcTimeToLoadingThisFlight() {
        int actTime = CalcTimeUtil.calcTimeToLoadingThisFlight(ScheduleData.getOrders());
        int expTime = ScheduleData.TIME_TO_LOADING_THIS_FLIGHT;
        Assertions.assertEquals(expTime, actTime);
    }

    @Test
    void calcTimeToStartLoadingThisFlight() {
        LocalTime actTime = CalcTimeUtil.calcTimeToStartLoadingThisFlight(
                ScheduleData.TIME_START_1_TIME_WINDOW, ScheduleData.TIME_TO_LOADING_THIS_FLIGHT,
                ScheduleData.TIME_START_WORK_DC, ScheduleData.DRIVING_TIME_DC_POINT1
        );

        LocalTime expTime = ScheduleData.TIME_TO_START_LOADING_THIS_FLIGHT;
        Assertions.assertEquals(expTime, actTime);
    }

    @Test
    void calcTimeGoToOutDC() {
        LocalTime actTime = CalcTimeUtil.calcTimeGoToOutDC(ScheduleData.TIME_TO_START_LOADING_THIS_FLIGHT,
                ScheduleData.TIME_TO_LOADING_THIS_FLIGHT);
        LocalTime expTime = ScheduleData.TIME_GO_TO_OUT_DC;
        Assertions.assertEquals(expTime, actTime);
    }

    @Test
    void calcTimeToStartUnloadingFirstOrder() {
        LocalTime actTime = CalcTimeUtil.calcTimeToStartUnloadingFirstOrder(
                ScheduleData.TIME_GO_TO_OUT_DC, ScheduleData.DRIVING_TIME_DC_POINT1
        );
        LocalTime expTime = ScheduleData.TIME_TO_START_UNLOADING_FIRST_ORDER;
        Assertions.assertEquals(expTime, actTime);
    }

    @Test
    void calcTimeToEndUnloadingFirstOrder() {
        LocalTime actTime = CalcTimeUtil.calcTimeToEndUnloadingFirstOrder(
                ScheduleData.TIME_TO_START_UNLOADING_FIRST_ORDER, ScheduleData.TIME_TO_UNLOADING_FIRST_ORDER
        );
        LocalTime expTime = ScheduleData.TIME_TO_END_UNLOADING_FIRST_ORDER;
        Assertions.assertEquals(expTime, actTime);
    }

    @Test
    void calcTimeToStartUnloadingOrder() {
        LocalTime actTime = CalcTimeUtil.calcTimeToStartUnloadingOrder(ScheduleData.TIME_START_2_TIME_WINDOW,
                ScheduleData.TIME_TO_END_UNLOADING_FIRST_ORDER, ScheduleData.DRIVING_TIME_POINT1_POINT2
        );
        LocalTime expTime = ScheduleData.TIME_TO_START_UNLOADING_ORDER;
        Assertions.assertEquals(expTime, actTime);
    }

    @Test
    void calcTimeToEndUnloadingOrder() {
        LocalTime actTime = CalcTimeUtil.calcTimeToEndUnloadingOrder(
                ScheduleData.TIME_TO_START_UNLOADING_ORDER, ScheduleData.TIME_TO_UNLOADING_CURRENT_ORDER
        );
        LocalTime expTime = ScheduleData.TIME_TO_END_UNLOADING_CURRENT_ORDER;
        Assertions.assertEquals(expTime, actTime);
    }

    @Test
    void calcTimeToReturnInDC() {
        LocalTime actTime = CalcTimeUtil.calcTimeToReturnInDC(
                ScheduleData.TIME_TO_END_UNLOADING_CURRENT_ORDER, ScheduleData.DRIVING_TIME_POINT3_DC
        );
        LocalTime expTime = ScheduleData.TIME_TO_RETURN_IN_DC;
        Assertions.assertEquals(expTime, actTime);
    }


}