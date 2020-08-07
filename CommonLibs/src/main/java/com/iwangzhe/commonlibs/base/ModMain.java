package com.iwangzhe.commonlibs.base;

abstract public class ModMain extends MyObject {
    abstract public String getModName();

    protected EModStatus mStatus = EModStatus.UNCREATE;
    protected EAppPhase mPhase = EAppPhase.APP_PHASE_BORN;

    public void born() {
        super.born();
        mStatus = EModStatus.CREATE;
    }

    public void create() {
        super.create();
        mStatus = EModStatus.INIT;
    }

    public void active() {
        super.active();
        mStatus = EModStatus.ACTIVE;
    }

    public void phase(EAppPhase phase) {
        mPhase = phase;
    }

    public void deactive() {
        super.deactive();
        mStatus = EModStatus.DEACTIVE;
    }

    public void destroy() {
        super.destroy();
        mStatus = EModStatus.DESTROY;
    }

    public void terminate() {
        super.terminate();
        mStatus = EModStatus.TERMINATE;
    }

}

