package sanandreasp.mods.turretmod3.entity.turret;

import sanandreasp.mods.turretmod3.entity.projectile.TurretProj_Laser;
import sanandreasp.mods.turretmod3.entity.projectile.TurretProjectile;
import sanandreasp.mods.turretmod3.registry.TM3ModRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.world.World;

public class EntityTurret_T3Laser extends EntityTurret_Base {
	
	private boolean isRight = false;

	public EntityTurret_T3Laser(World par1World) {
		super(par1World);
		this.wdtRange = 32.5F;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getTexture() {
		return TM3ModRegistry.TEX_TURRETDIR + "t3Laser.png";
	}
	
	@Override
	public int func_110138_aP() {
		return 60;
	}

	@Override
	public TurretProjectile getProjectile() {
		return new TurretProj_Laser(this.worldObj);
	}
	
	@Override
	public void shootProjectile(boolean isRidden) {
		super.shootProjectile(isRidden);
		double rotYawX = Math.sin((this.rotationYawHead / 180) * Math.PI);
		double rotYawZ = Math.cos((this.rotationYawHead / 180) * Math.PI);
		double partX = this.posX - rotYawX * (Math.cos(this.rotationPitch / (180F / (float)Math.PI))) * 0.7D;
		double partY = this.posY + this.getEyeHeight() - Math.sin(this.rotationPitch / (180F / (float)Math.PI)) * 0.7D;
		double partZ = this.posZ + rotYawZ * (Math.cos(this.rotationPitch / (180F / (float)Math.PI))) * 0.7D;
		
		TM3ModRegistry.proxy.spawnParticle(1, partX - (this.isRight ? 0.1D : -0.1D)*rotYawZ, partY, partZ - (this.isRight ? 0.1D : -0.1D)*rotYawX, 64, this.worldObj.provider.dimensionId, this);
	}
	
	@Override
	public int getMaxShootTicks() {
		int maxShootTicks = 5;
		if (!this.worldObj.isRemote) {
			this.isRight = !this.isRight;
		}
		return maxShootTicks;
	}

	@Override
	public String getGlowTexture() {
		return TM3ModRegistry.TEX_TURRETDIR + "t3LaserG.png";
	}
	
	@Override
	public String getShootSound() {
		return "turretmod3.shoot.laser";
	}
}