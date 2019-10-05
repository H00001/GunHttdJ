package top.gunplan.netty.httpd.protocols;

/**
 * @deprecated
 */
public class GunAmqpHeartProtocl extends AbstractGunAmqpProtocol {
    private byte[] prot = new byte[8];

    public GunAmqpHeartProtocl() {
        super();
    }

    @Override
    public boolean unSerialize(byte[] in) {
        return false;
    }

    @Override
    public byte[] serialize() {
        prot[0] = RealType.HEART.val;
        System.arraycopy(channel, 0, prot, 1, Lengths.CHANNEL.len);
        System.arraycopy(channel, 0, prot, 3, Lengths.LENGTH.len);
        prot[7] = endflag;
        return prot;
    }

}
