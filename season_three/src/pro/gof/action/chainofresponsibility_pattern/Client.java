package pro.gof.action.chainofresponsibility_pattern;

import org.junit.Test;

/**
 * Created by near on 2015/12/12.
 */
public class Client {
    @Test
    public void test() {
        LeaveRequest leaveRequest = new LeaveRequest("三胖", 8, "有病");

        Leader leader = new Director("jack");
        Leader leader2 = new Manager("tom");
        Leader leader3 = new Boss("near");

        leader.setNextLeader(leader2);
        leader2.setNextLeader(leader3);

        leader.handleRequest(leaveRequest);
    }
}

class LeaveRequest {
    private String empName;
    private int leaveDays;
    private String reason;

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public int getLeaveDays() {
        return leaveDays;
    }

    public void setLeaveDays(int leaveDays) {
        this.leaveDays = leaveDays;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LeaveRequest(String empName, int leaveDays, String reason) {
        this.empName = empName;
        this.leaveDays = leaveDays;
        this.reason = reason;
    }

    public void requestLeave() {
    }
}

abstract class Leader {
    protected String name;
    protected Leader nextLeader;

    public void setNextLeader(Leader nextLeader) {
        this.nextLeader = nextLeader;
    }

    public Leader(String name) {
        this.name = name;
    }

    public abstract void handleRequest(LeaveRequest leaveRequest);
}

class Director extends Leader {
    public Director(String name) {
        super(name);
    }

    @Override
    public void handleRequest(LeaveRequest leaveRequest) {
        if (leaveRequest.getLeaveDays() <= 3) {
            System.out.println(leaveRequest.getEmpName() + " 请假" + leaveRequest.getLeaveDays());
            System.out.println("主管 " + this.name + " 批准通过");
        } else {
            if (this.nextLeader != null) {
                this.nextLeader.handleRequest(leaveRequest);
            }
        }
    }
}

class Manager extends Leader {
    public Manager(String name) {
        super(name);
    }

    @Override
    public void handleRequest(LeaveRequest leaveRequest) {
        if (leaveRequest.getLeaveDays() <= 10) {
            System.out.println(leaveRequest.getEmpName() + " 请假" +
                    leaveRequest.getLeaveDays() + "天 原因：" + leaveRequest.getReason());
            System.out.println("经理 " + this.name + " 批准通过");
        } else {
            if (this.nextLeader != null) {
                this.nextLeader.handleRequest(leaveRequest);
            }
        }
    }
}

class Boss extends Leader {
    public Boss(String name) {
        super(name);
    }

    @Override
    public void handleRequest(LeaveRequest leaveRequest) {
        System.out.println(leaveRequest.getEmpName() + " 请假" + leaveRequest.getLeaveDays());
        System.out.println("Boss " + this.name + " 批准通过");
    }
}