package org.l2j.gameserver.model.items;

import org.l2j.gameserver.model.ExtractableProduct;
import org.l2j.gameserver.model.StatsSet;
import org.l2j.gameserver.model.items.type.EtcItemType;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is dedicated to the management of EtcItem.
 */
public final class EtcItem extends ItemTemplate {
    private String handler;
    private EtcItemType type;
    private List<ExtractableProduct> _extractableItems;
    private int _extractableCountMin;
    private int _extractableCountMax;
    private boolean isInfinite;

    /**
     * Constructor for EtcItem.
     *
     * @param set StatsSet designating the set of couples (key,value) for description of the Etc
     */
    public EtcItem(StatsSet set) {
        super(set);
    }

    public EtcItem(int id, String name, EtcItemType type) {
        super(id, name);
        this.type = type;
    }

    @Override
    public void set(StatsSet set) {
        super.set(set);
        type = set.getEnum("etcitem_type", EtcItemType.class, EtcItemType.NONE);
        _type1 = ItemTemplate.TYPE1_ITEM_QUESTITEM_ADENA;

        if (isQuestItem()) {
            _type2 = ItemTemplate.TYPE2_QUEST;
        } else {
            _type2 = switch (getId()) {
                case CommonItem.ADENA, CommonItem.ANCIENT_ADENA, CommonItem.RUSTY_COIN, CommonItem.SILVER_COIN -> ItemTemplate.TYPE2_MONEY;
                default -> ItemTemplate.TYPE2_OTHER;
            };
        }

        handler = set.getString("handler", null); // ! null !

        _extractableCountMin = set.getInt("extractableCountMin", 0);
        _extractableCountMax = set.getInt("extractableCountMax", 0);
        if (_extractableCountMin > _extractableCountMax) {
            LOGGER.warn("Item " + this + " extractableCountMin is bigger than extractableCountMax!");
        }

        isInfinite = set.getBoolean("is_infinite", false);
    }

    /**
     * @return the type of Etc Item.
     */
    @Override
    public EtcItemType getItemType() {
        return type;
    }

    /**
     * @return the ID of the Etc item after applying the mask.
     */
    @Override
    public int getItemMask() {
        return type.mask();
    }

    /**
     * @return the handler name, null if no handler for item.
     */
    public String getHandlerName() {
        return handler;
    }

    /**
     * @return the extractable items list.
     */
    public List<ExtractableProduct> getExtractableItems() {
        return _extractableItems;
    }

    /**
     * @return the minimum count of extractable items
     */
    public int getExtractableCountMin() {
        return _extractableCountMin;
    }

    /**
     * @return the maximum count of extractable items
     */
    public int getExtractableCountMax() {
        return _extractableCountMax;
    }

    /**
     * @return true if item is infinite
     */
    public boolean isInfinite() {
        return isInfinite;
    }

    /**
     * @param extractableProduct
     */
    @Override
    public void addCapsuledItem(ExtractableProduct extractableProduct) {
        if (_extractableItems == null) {
            _extractableItems = new ArrayList<>();
        }
        _extractableItems.add(extractableProduct);
    }

    public void setImmediateEffect(boolean immediateEffect) {
        this.immediateEffect = immediateEffect;
    }

    public void setExImmediateEffect(boolean exImmediateEffect) {
        this.exImmediateEffect = exImmediateEffect;
    }

    public void setQuestItem(boolean questItem) {
        this.questItem = questItem;
    }

    public void setInfinite(boolean infinite) {
        this.isInfinite = infinite;
    }

    public void setSelfResurrection(boolean selfResurrection) {
        allowSelfResurrection = selfResurrection;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }
}
