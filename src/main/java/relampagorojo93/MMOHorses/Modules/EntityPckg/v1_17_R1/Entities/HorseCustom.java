package relampagorojo93.MMOHorses.Modules.EntityPckg.v1_17_R1.Entities;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_17_R1.inventory.CraftItemStack;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityAgeable;
import net.minecraft.world.entity.animal.EntityAnimal;
import net.minecraft.world.entity.animal.horse.EntityHorse;
import net.minecraft.world.entity.animal.horse.EntityHorseAbstract;
import net.minecraft.world.entity.animal.horse.HorseColor;
import net.minecraft.world.entity.animal.horse.HorseStyle;
import net.minecraft.world.entity.player.EntityHuman;
import net.minecraft.world.item.ItemHorseArmor;
import net.minecraft.server.level.EntityPlayer;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.EnumMobSpawn;
import net.minecraft.world.entity.GroupDataEntity;
import net.minecraft.world.DifficultyDamageScaler;
import net.minecraft.world.InventorySubcontainer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.protocol.game.PacketPlayOutAttachEntity;
import net.minecraft.world.entity.ai.goal.PathfinderGoalSelector;
import net.minecraft.world.level.World;
import net.minecraft.world.level.WorldAccess;
import net.minecraft.server.level.WorldServer;
import relampagorojo93.MMOHorses.Enums.Colour;
import relampagorojo93.MMOHorses.Enums.Marking;
import relampagorojo93.MMOHorses.Enums.Type;
import relampagorojo93.MMOHorses.Modules.EntityPckg.Interfaces.Objects.MMOHorseData;
import relampagorojo93.MMOHorses.Modules.EntityPckg.Interfaces.Objects.MMOHorseMethods;
import relampagorojo93.MMOHorses.Modules.EntityPckg.Interfaces.Objects.NBT.StdDamageSource;
import relampagorojo93.MMOHorses.Modules.EntityPckg.Interfaces.Objects.NBT.StdEntity;
import relampagorojo93.MMOHorses.Modules.EntityPckg.Interfaces.Objects.NBT.StdEntityHuman;
import relampagorojo93.MMOHorses.Modules.EntityPckg.Interfaces.Objects.NBT.StdEntityPlayer;
import relampagorojo93.MMOHorses.Modules.EntityPckg.Interfaces.Objects.NBT.StdPacket;
import relampagorojo93.MMOHorses.Modules.EntityPckg.v1_17_R1.NBTTags.NMSDamageSource;
import relampagorojo93.MMOHorses.Modules.EntityPckg.v1_17_R1.NBTTags.NMSEntity;
import relampagorojo93.MMOHorses.Modules.EntityPckg.v1_17_R1.NBTTags.NMSEntityAnimal;
import relampagorojo93.MMOHorses.Modules.EntityPckg.v1_17_R1.NBTTags.NMSEntityHuman;
import relampagorojo93.MMOHorses.Modules.EntityPckg.v1_17_R1.NBTTags.NMSEntityPlayer;
import relampagorojo93.MMOHorses.Modules.EntityPckg.v1_17_R1.NBTTags.NMSPacket;
import relampagorojo93.MMOHorses.Modules.FilePckg.Settings.SettingBoolean;

public class HorseCustom extends EntityHorse implements NMSMMOHorse {

	// ---------------------------------------------------------------------//
	// Objects
	// ---------------------------------------------------------------------//
	
	private MMOHorseData data = new MMOHorseData(this);
	private boolean custom = false;

	// ---------------------------------------------------------------------//
	// Entity initializers
	// ---------------------------------------------------------------------//
	
	public HorseCustom(EntityTypes<? extends EntityHorse> entitytypes, World w) { // Required
		super(entitytypes, w);
		data.initialize();
	}

	public HorseCustom(Location l) {
		this(EntityTypes.M, ((CraftWorld) l.getWorld()).getHandle());
		this.setLocation(l.getX(), l.getY(), l.getZ(), l.getYaw(), l.getPitch());
		((CraftWorld) l.getWorld()).getHandle().addEntity(this);
	}

	// ---------------------------------------------------------------------//
	// Class methods
	// ---------------------------------------------------------------------//

	public MMOHorseData getMMOHorseData() {
		return data;
	}
	
	public Type getType() {
		return Type.HORSE;
	}

	@Override
	public HorseCustom createEntity(org.bukkit.World world) {
		return createEntity(((CraftWorld) world).getHandle());
	}

	public HorseCustom createEntity(World world) {
		return new HorseCustom(EntityTypes.M, world);
	}

	@Override
	public void setAsCustom(boolean custom) {
		this.custom = custom;
	}

	@Override
	public boolean isCustom() {
		return custom;
	}

	// ---------------------------------------------------------------------//
	// Super methods
	// ---------------------------------------------------------------------//

	@Override
	public void superSaveData(NBTTagCompound nbtcompound) {
		super.saveData(nbtcompound);
	}

	@Override
	public void superLoadData(NBTTagCompound nbtcompound) {
		super.loadData(nbtcompound);
	}

	@Override
	public void superInitPathfinder() {
		super.initPathfinder();
	}
	
	@Override
	public GroupDataEntity superPrepare(WorldAccess worldaccess, DifficultyDamageScaler difficultydamagescaler, EnumMobSpawn enummobspawn, GroupDataEntity groupdataentity, NBTTagCompound nbttagcompound) {
		return super.prepare(worldaccess, difficultydamagescaler, enummobspawn, groupdataentity, nbttagcompound);
	}

	@Override
	public boolean superIsInvulnerable(StdDamageSource damagesource) {
		return super.isInvulnerable(((NMSDamageSource) damagesource).getDamageSource());
	}

	@Override
	public void superTick() {
		super.tick();
	}

	@Override
	public boolean superAddPassenger(StdEntity entity, boolean flag) {
		return super.addPassenger(((NMSEntity) entity).getEntity());
	}

	@Override
	public void superDie() {
		super.a(RemovalReason.b);
	}

	public void superDie(StdDamageSource damagesource) {
		super.die(((NMSDamageSource) damagesource).getDamageSource());
	}

	@Override
	public boolean superIsDropExperience() {
		return super.isDropExperience();
	}

	@Override
	public void superJump(int jump) {
		super.b(jump);
	}

	@Override
	public void superOpenInventory(StdEntityHuman entityhuman) {
		super.f(((NMSEntityHuman) entityhuman).getEntityHuman());
	}

	@Override
	public boolean superSetLeashHolder(StdEntityHuman entityhuman) {
		return super.a(((NMSEntityHuman) entityhuman).getEntityHuman());
	}

	@Override
	public void superEnterLoveMode(Player player) {
		super.g(((CraftPlayer) player).getHandle());
	}

	@Override
	public StdEntityPlayer superGetBreedCause() {
		return super.getBreedCause() != null ? new NMSEntityPlayer(super.getBreedCause()) : null;
	}
	
	@Override
	public int superGetVariantRaw() {
		return getColor().a() & 0xFF | getStyle().a() << 8 & 0xFF00;
	}

	// ---------------------------------------------------------------------//
	// NMS methods
	// ---------------------------------------------------------------------//

	@Override
	public StdPacket nmsLeashHolderPacket() {
		return new NMSPacket(new PacketPlayOutAttachEntity(this, getLeashHolder()));
	}

	@Override
	public boolean dD() {
		return isDropExperience();
	}

	@Override
	public void dropInventory() {
		if (!getMMOHorseData().isClaimed())
			super.dropInventory();
	}

	// ---------------------------------------------------------------------//
	// Translated methods
	// ---------------------------------------------------------------------//

	@Override
	public void saveData(NBTTagCompound nbttagcompound) {
		customSaveData(nbttagcompound);
	}

	@Override
	public void loadData(NBTTagCompound nbttagcompound) {
		customLoadData(nbttagcompound);
	}

	@Override
	protected void initPathfinder() {
		customInitPathfinder();
	}
	
	@Override
	public GroupDataEntity prepare(WorldAccess worldaccess, DifficultyDamageScaler difficultydamagescaler, EnumMobSpawn enummobspawn, GroupDataEntity groupdataentity, NBTTagCompound nbttagcompound) {
		return customPrepare(worldaccess, difficultydamagescaler, enummobspawn, groupdataentity, nbttagcompound);
	}

	@Override
	public boolean isInvulnerable(DamageSource damagesource) {
		if (!custom)
			return super.isInvulnerable(damagesource);
		else
			return MMOHorseMethods.customIsInvulnerable(this, new NMSDamageSource(damagesource));
	}

	@Override
	public void tick() {
		if (custom)
			MMOHorseMethods.customTick(this);
		super.tick();
	}

	@Override
	protected boolean addPassenger(net.minecraft.world.entity.Entity entity) {
		if (!custom)
			return super.addPassenger(entity);
		else
			return MMOHorseMethods.customAddPassenger(this,
					entity instanceof EntityPlayer ? new NMSEntityPlayer((EntityPlayer) entity) : new NMSEntity(entity),
					false);
	}

	@Override
	public boolean a(EntityHuman entityhuman) {
		if (!custom)
			return super.a(entityhuman);
		else
			return MMOHorseMethods.customSetLeashHolder(this, new NMSEntityHuman(entityhuman));
	}

	@Override
	public void a(RemovalReason removalreason) {
		if (RemovalReason.b == removalreason) {
			if (!custom)
				super.a(removalreason);
			else
				MMOHorseMethods.customDie(this);
		}
		else
			super.a(removalreason);
	}

	@Override
	public void die(DamageSource damagesource) {
		if (!custom)
			super.die(damagesource);
		else
			MMOHorseMethods.customDie(this, new NMSDamageSource(damagesource));
	}

	@Override
	public boolean isDropExperience() {
		if (!custom)
			return super.isDropExperience();
		else
			return MMOHorseMethods.customIsDropExperience(this);
	}

	@Override
	public void b(int jump) { // Required
		if (!custom)
			super.b(jump);
		else
			MMOHorseMethods.customJump(this, jump);
	}

	@Override
	public EntityAgeable createChild(WorldServer worldserver, EntityAgeable entityanimal) {
		if (!custom)
			return super.createChild(worldserver, entityanimal);
		else
			return (EntityAgeable) MMOHorseMethods.customCreateChild(this, this.t.getWorld(),
					entityanimal instanceof EntityAnimal ? new NMSEntityAnimal((EntityAnimal) entityanimal) : null);
	}

	// ---------------------------------------------------------------------//
	// MMO abstract methods
	// ---------------------------------------------------------------------//

	@Override
	public Entity getKiller() {
		return bc != null ? bc.getBukkitEntity() : null;
	}

	public EntityHorseAbstract getNMSEntity() {
		return this;
	}

	@Override
	public InventorySubcontainer getNMSInventory() {
		return ce;
	}

	@Override
	public void setRemoveWhenFarAway(boolean removewhenfaraway) {
		this.setPersistenceRequired(removewhenfaraway);
	}

	@Override
	public boolean isValid() {
		return isAlive() && this.valid && getLocation().getChunk().isLoaded();
	}

	@Override
	public void setYawPitch(float yaw, float pitch) {
		super.setYawPitch(yaw, pitch);
	}

	@Override
	public void setPathfinder(PathfinderGoalSelector goalSelector) {
		this.bP = goalSelector;
	}

	// ---------------------------------------------------------------------//
	// Entity specific methods
	// ---------------------------------------------------------------------//

	@Override
	public boolean l(net.minecraft.world.item.ItemStack var0) {
		return var0.getItem() instanceof ItemHorseArmor && SettingBoolean.MAKECOSMETICSFUNCTIONAL.toBoolean()
				&& getMMOHorseData().isClaimed() && getMMOHorseData().getClaimedData().getCosmetics().getBody() != null;
	}

	@Override
	public void updateVariant() {
		this.setVariant(getColor(), getStyle());
	}

	@Override
	public void setBody(ItemStack item) {
		getNMSInventory().setItem(1, CraftItemStack.asNMSCopy(item));
	}

	@Override
	public ItemStack getBody() {
		return CraftItemStack.asBukkitCopy(this.getNMSInventory().getItem(1));
	}

	@Override
	public HorseColor getColor() {
		Colour colour = getMMOHorseData().getColour();
		return HorseColor.a(colour != null && colour.toBukkitHorse() != null ? colour.toBukkitHorse().ordinal()
				: Colour.values()[0].toBukkitHorse().ordinal());
	}

	@Override
	public HorseStyle getStyle() {
		Marking marking = getMMOHorseData().getMarking();
		return HorseStyle.a(marking != null && marking.toBukkit() != null ? marking.toBukkit().ordinal()
				: Marking.NONE.toBukkit().ordinal());
	}

}