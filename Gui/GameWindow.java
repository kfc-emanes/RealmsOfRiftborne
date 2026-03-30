package Gui;

import Hero.Gunner;
import Hero.Hero;
import Hero.Mage;
import Hero.Swordsman;
import Inventory.Inventory;
import TrainingGround.StatProgress;
import Boss.Azrael;
import Boss.Elderthorn;
import Boss.Kim;
import Boss.Morgrath;
import Mobs.FadingWarden;
import Mobs.Goblin;
import Mobs.HollowKing;
import Mobs.MudLurker;
import Mobs.ShadowAbyss;
import Mobs.Slime;
import Mobs.SwampRat;
import Mobs.VeilSerpent;
import Mobs.VoidBeast;
import Hero.Entity;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.imageio.ImageIO;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GameWindow {
    private static final String UI_IMAGE_DIRECTORY = "assets/images/ui/";
    private static final String ENEMY_IMAGE_DIRECTORY = "assets/images/enemies/";
    private static final String ROOT_LANDING = "landing";
    private static final String ROOT_GAME = "game";
    private static final String SCREEN_HOME = "home";
    private static final String SCREEN_CHARACTER = "character";
    private static final String SCREEN_STORY = "story";
    private static final String SCREEN_MAIN = "main";
    private static final String SCREEN_ACADEMY = "academy";
    private static final String SCREEN_INVENTORY = "inventory";
    private static final String SCREEN_PROFILE = "profile";
    private static final String SCREEN_BATTLE = "battle";

    private final DecimalFormat statFormat = new DecimalFormat("#,##0");
    private final Random random = new Random();
    private final StatProgress statProgress = new StatProgress();

    private final JTextArea outputArea = new JTextArea();
    private final JTextField inputField = new JTextField();
    private final JButton sendButton = new JButton("Enter");
    private final StringBuilder pendingLegacyOutput = new StringBuilder();
    private static final int LEGACY_OUTPUT_TAIL_LIMIT = 500;
    private static final Pattern BOXED_YES_NO_PROMPT = Pattern.compile(
        "(?s)\\n?┌[^\\n]*┐\\n(?:│[^\\n]*\\n)+?└[^\\n]*┘\\n?(?:-->\\|\\s*)?"
    );
    private static final Pattern INLINE_YES_NO_PROMPT = Pattern.compile(
        "\\n?[^\\n]*\\(y/n\\):\\s*"
    );

    private final CardLayout rootLayout = new CardLayout();
    private final JPanel rootPanel = new JPanel(rootLayout);
    private final CardLayout screenLayout = new CardLayout();
    private final JPanel screenPanel = new JPanel(screenLayout);

    private final JLabel titleLabel = new JLabel("Mystvale Academy");
    private final JLabel subtitleLabel = new JLabel("GUI RPG");

    private final JLabel heroNameValue = new JLabel("-");
    private final JLabel heroClassValue = new JLabel("-");
    private final JLabel heroLevelValue = new JLabel("-");
    private final JLabel heroGoldValue = new JLabel("-");
    private final JProgressBar hpBar = new JProgressBar();
    private final JProgressBar manaBar = new JProgressBar();

    private final JLabel inventorySummary = new JLabel("No hero selected.");
    private final JLabel smallHealthCount = new JLabel("0");
    private final JLabel mediumHealthCount = new JLabel("0");
    private final JLabel largeHealthCount = new JLabel("0");
    private final JLabel smallManaCount = new JLabel("0");
    private final JLabel mediumManaCount = new JLabel("0");
    private final JLabel largeManaCount = new JLabel("0");

    private final JLabel profileName = new JLabel("-");
    private final JLabel profileClass = new JLabel("-");
    private final JLabel profileWeapon = new JLabel("-");
    private final JLabel profileLevel = new JLabel("-");
    private final JLabel profileAttack = new JLabel("-");
    private final JLabel profileDefense = new JLabel("-");
    private final JLabel profileSpeed = new JLabel("-");
    private final JLabel profileSkill1 = new JLabel("-");
    private final JLabel profileSkill2 = new JLabel("-");
    private final JLabel profileUltimate = new JLabel("-");
    private final JTextArea storyTextArea = new JTextArea();
    private final JLabel storyTitleLabel = new JLabel("Story");
    private final JLabel storyProgressLabel = new JLabel("Scene 1 / 1");

    private JButton forestButton;
    private JButton swampButton;
    private JButton forsakenButton;
    private JButton storyNextButton;
    private JButton storySkipButton;
    private JButton storyStartButton;
    private JPanel quickChoicePanel;
    private JLabel quickChoicePromptLabel;
    private JButton yesChoiceButton;
    private JButton noChoiceButton;

    private final JLabel battleTitleValue = new JLabel("Battle");
    private final JLabel battleRoundValue = new JLabel("Round 1");
    private final JLabel battleHeroNameValue = new JLabel("-");
    private final JLabel battleEnemyNameValue = new JLabel("-");
    private final JLabel battleEnemySpriteLabel = new JLabel("No Sprite", SwingConstants.CENTER);
    private final JProgressBar battleHeroHpBar = new JProgressBar();
    private final JProgressBar battleHeroManaBar = new JProgressBar();
    private final JProgressBar battleEnemyHpBar = new JProgressBar();
    private final JProgressBar battleEnemyManaBar = new JProgressBar();
    private final JTextArea battleLogArea = new JTextArea();
    private JButton battleBasicButton;
    private JButton battleSkill1Button;
    private JButton battleSkill2Button;
    private JButton battleUltimateButton;
    private JButton battlePotionButton;
    private JButton battleRunButton;
    private JButton battleBackButton;

    private final Object battleActionLock = new Object();
    private volatile Integer pendingBattleAction = null;

    private Hero hero;
    private String[] currentStorySequence = new String[0];
    private int currentStoryIndex = 0;
    private JFrame frame;
    private BufferedImage landingBackground;
    private final ButtonSkin primaryButtonSkin;
    private final ButtonSkin secondaryButtonSkin;

    private final PipedInputStream gameInputStream;
    private final PipedOutputStream gameInputWriter;

    public GameWindow() {
        try {
            gameInputStream = new PipedInputStream();
            gameInputWriter = new PipedOutputStream(gameInputStream);
        } catch (IOException exception) {
            throw new IllegalStateException("Unable to initialize GUI input stream.", exception);
        }

        try {
            // Change the main screen background here by replacing the file path below
            // with your own image inside assets/images.
            // The landing screen uses a regular raster image file, so artwork exported
            // from Aseprite can be dropped into assets/images and loaded the same way.
            landingBackground = ImageIO.read(new File("assets/images/landing-background-source.jpg"));
        } catch (IOException ignored) {
            // If the image is missing or unreadable, we keep the game running and fall
            // back to a solid-color title screen instead of crashing on startup.
            landingBackground = null;
        }

        primaryButtonSkin = loadButtonSkin("button-primary");
        secondaryButtonSkin = loadButtonSkin("button-secondary");
    }

    public void launchGame() {
        wireConsoleStreams();
        frame = buildFrame();
        frame.setVisible(true);
        showLandingScreen();
    }

    private JFrame buildFrame() {
        JFrame window = new JFrame("Mystvale Academy RPG");
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setMinimumSize(new Dimension(1280, 800));
        window.setContentPane(rootPanel);

        rootPanel.add(buildLandingScreen(), ROOT_LANDING);
        rootPanel.add(buildGameShell(), ROOT_GAME);

        return window;
    }

    private JPanel buildGameShell() {
        JPanel panel = new JPanel(new BorderLayout(18, 18));
        panel.setBackground(new Color(238, 233, 224));

        JPanel header = buildHeader();
        JPanel leftPane = buildLeftPane();

        panel.add(header, BorderLayout.NORTH);
        panel.add(leftPane, BorderLayout.CENTER);
        return panel;
    }

    private JPanel buildHeader() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(18, 22, 0, 22));
        panel.setBackground(new Color(238, 233, 224));

        titleLabel.setFont(new Font("Serif", Font.BOLD, 30));
        titleLabel.setForeground(new Color(52, 33, 18));

        subtitleLabel.setFont(new Font("SansSerif", Font.PLAIN, 15));
        subtitleLabel.setForeground(new Color(95, 74, 58));

        panel.add(titleLabel);
        panel.add(Box.createVerticalStrut(4));
        panel.add(subtitleLabel);
        return panel;
    }

    private JPanel buildLeftPane() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setPreferredSize(new Dimension(1220, 720));
        panel.setBorder(BorderFactory.createEmptyBorder(0, 22, 22, 22));
        panel.setBackground(new Color(238, 233, 224));

        screenPanel.setOpaque(false);
        screenPanel.add(buildHomeScreen(), SCREEN_HOME);
        screenPanel.add(buildCharacterScreen(), SCREEN_CHARACTER);
        screenPanel.add(buildStoryScreen(), SCREEN_STORY);
        screenPanel.add(buildMainScreen(), SCREEN_MAIN);
        screenPanel.add(buildAcademyScreen(), SCREEN_ACADEMY);
        screenPanel.add(buildInventoryScreen(), SCREEN_INVENTORY);
        screenPanel.add(buildProfileScreen(), SCREEN_PROFILE);
        screenPanel.add(buildBattleScreen(), SCREEN_BATTLE);

        panel.add(screenPanel, BorderLayout.CENTER);
        return panel;
    }

    private JPanel buildRightPane() {
        JPanel panel = new JPanel(new BorderLayout(0, 12));
        panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 22, 22));
        panel.setBackground(new Color(238, 233, 224));

        outputArea.setEditable(false);
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);
        outputArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
        outputArea.setBackground(new Color(20, 24, 31));
        outputArea.setForeground(new Color(234, 239, 245));
        outputArea.setMargin(new Insets(14, 14, 14, 14));

        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(61, 48, 39), 1),
            BorderFactory.createEmptyBorder(0, 0, 0, 0)
        ));

        JPanel inputPanel = new JPanel(new BorderLayout(8, 8));
        inputPanel.setBackground(new Color(238, 233, 224));

        JLabel inputHelp = new JLabel("Game input");
        inputHelp.setForeground(new Color(95, 74, 58));

        inputField.addActionListener(event -> submitLegacyInput());
        sendButton.addActionListener(event -> submitLegacyInput());

        JButton clearButton = createSecondaryButton("Clear Log");
        clearButton.addActionListener(event -> outputArea.setText(""));

        JPanel row = new JPanel(new BorderLayout(8, 0));
        row.setOpaque(false);
        row.add(inputField, BorderLayout.CENTER);
        row.add(sendButton, BorderLayout.EAST);

        quickChoicePanel = new JPanel(new GridLayout(1, 2, 8, 0));
        quickChoicePanel.setOpaque(false);
        quickChoicePromptLabel = new JLabel("Choose:");
        quickChoicePromptLabel.setForeground(new Color(95, 74, 58));
        quickChoicePromptLabel.setVisible(false);
        yesChoiceButton = createSecondaryButton("Yes");
        noChoiceButton = createSecondaryButton("No");
        yesChoiceButton.addActionListener(event -> submitLegacyShortcut("y"));
        noChoiceButton.addActionListener(event -> submitLegacyShortcut("n"));
        quickChoicePanel.add(yesChoiceButton);
        quickChoicePanel.add(noChoiceButton);
        quickChoicePanel.setVisible(false);

        JPanel footer = new JPanel(new BorderLayout());
        footer.setOpaque(false);
        footer.add(inputHelp, BorderLayout.CENTER);
        footer.add(clearButton, BorderLayout.EAST);

        inputPanel.add(footer, BorderLayout.NORTH);
        inputPanel.add(row, BorderLayout.CENTER);

        JPanel quickChoiceWrapper = new JPanel(new BorderLayout(0, 6));
        quickChoiceWrapper.setOpaque(false);
        quickChoiceWrapper.add(quickChoicePromptLabel, BorderLayout.NORTH);
        quickChoiceWrapper.add(quickChoicePanel, BorderLayout.CENTER);
        inputPanel.add(quickChoiceWrapper, BorderLayout.SOUTH);

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(inputPanel, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel buildHomeScreen() {
        JPanel panel = createCardPanel();

        JLabel heading = createHeading("Main Screen");
        JLabel body = createBody("Choose your hero and begin.");

        JButton chooseHeroButton = createPrimaryButton("Choose Hero");
        chooseHeroButton.addActionListener(event -> {
            subtitleLabel.setText("Choose a hero to begin.");
            showScreen(SCREEN_CHARACTER);
        });

        JButton titleScreenButton = createSecondaryButton("Return to Title");
        titleScreenButton.addActionListener(event -> showLandingScreen());

        panel.add(heading);
        panel.add(Box.createVerticalStrut(10));
        panel.add(body);
        panel.add(Box.createVerticalStrut(18));
        panel.add(chooseHeroButton);
        panel.add(Box.createVerticalStrut(10));
        panel.add(titleScreenButton);
        panel.add(Box.createVerticalGlue());
        return panel;
    }

    private JPanel buildCharacterScreen() {
        JPanel panel = createCardPanel();

        panel.add(createHeading("Choose Character"));
        panel.add(Box.createVerticalStrut(8));
        panel.add(createBody("Pick your character."));
        panel.add(Box.createVerticalStrut(18));

        // If you later add separate image paths per hero, these are the three
        // createCharacterButton(...) calls to update.
        panel.add(createCharacterButton(
            "Swordsman",
            "Frontline fighter",
            () -> selectHero(new Swordsman())
        ));
        panel.add(Box.createVerticalStrut(10));
        panel.add(createCharacterButton(
            "Gunner",
            "Ranged attacker",
            () -> selectHero(new Gunner())
        ));
        panel.add(Box.createVerticalStrut(10));
        panel.add(createCharacterButton(
            "Mage",
            "Spell caster",
            () -> selectHero(new Mage())
        ));
        panel.add(Box.createVerticalStrut(18));

        JButton backButton = createSecondaryButton("Back to Main Page");
        backButton.addActionListener(event -> {
            showLandingScreen();
        });

        panel.add(backButton);
        panel.add(Box.createVerticalGlue());
        return panel;
    }

    private JPanel buildMainScreen() {
        JPanel panel = createCardPanel();

        panel.add(createHeading("Character Overview"));
        panel.add(Box.createVerticalStrut(10));
        panel.add(buildHeroSummaryCard());
        panel.add(Box.createVerticalStrut(16));

        JButton academyButton = createPrimaryButton("Academy Map");
        academyButton.addActionListener(event -> {
            if (requireHero()) {
                hero.setHasVisitedAcademy(true);
                subtitleLabel.setText("Explore Mystvale Academy.");
                showScreen(SCREEN_ACADEMY);
            }
        });

        JButton inventoryButton = createPrimaryButton("Inventory");
        inventoryButton.addActionListener(event -> {
            if (requireHero()) {
                refreshInventoryPanel();
                subtitleLabel.setText("Manage your items.");
                showScreen(SCREEN_INVENTORY);
            }
        });

        JButton storyButton = createSecondaryButton("Storyline");
        storyButton.addActionListener(event -> {
            if (requireHero()) {
                beginStoryForHero(false);
            }
        });

        forestButton = createPrimaryButton("Forest of Reverie");
        forestButton.addActionListener(event -> launchArea1());

        swampButton = createPrimaryButton("Reverie's Edge");
        swampButton.addActionListener(event -> launchArea2());

        forsakenButton = createPrimaryButton("Forsaken Lands");
        forsakenButton.addActionListener(event -> launchArea3());

        JButton profileButton = createSecondaryButton("Hero Details");
        profileButton.addActionListener(event -> {
            if (requireHero()) {
                refreshProfilePanel();
                subtitleLabel.setText("Review hero stats and skills.");
                showScreen(SCREEN_PROFILE);
            }
        });

        JButton changeHeroButton = createSecondaryButton("Change Character");
        changeHeroButton.addActionListener(event -> {
            if (hero != null) {
                int choice = JOptionPane.showConfirmDialog(
                    frame,
                    "Switching character resets current run progress. Continue?",
                    "Change Character",
                    JOptionPane.YES_NO_OPTION
                );

                if (choice == JOptionPane.YES_OPTION) {
                    hero.resetAllProgress();
                    hero = null;
                    subtitleLabel.setText("Choose a hero to begin.");
                    showScreen(SCREEN_CHARACTER);
                    appendLog("\nCharacter selection reopened.\n");
                }
            }
        });

        JButton exitButton = createSecondaryButton("Exit Game");
        exitButton.addActionListener(event -> frame.dispose());

        panel.add(academyButton);
        panel.add(Box.createVerticalStrut(10));
        panel.add(inventoryButton);
        panel.add(Box.createVerticalStrut(10));
        panel.add(storyButton);
        panel.add(Box.createVerticalStrut(10));
        panel.add(forestButton);
        panel.add(Box.createVerticalStrut(10));
        panel.add(swampButton);
        panel.add(Box.createVerticalStrut(10));
        panel.add(forsakenButton);
        panel.add(Box.createVerticalStrut(18));
        panel.add(profileButton);
        panel.add(Box.createVerticalStrut(10));
        panel.add(changeHeroButton);
        panel.add(Box.createVerticalStrut(10));
        panel.add(exitButton);
        panel.add(Box.createVerticalGlue());

        return panel;
    }

    private JPanel buildAcademyScreen() {
        JPanel panel = createCardPanel();

        panel.add(createHeading("Academy (Inside)"));
        panel.add(Box.createVerticalStrut(8));
        panel.add(createBody("Library, Training Ground, Shop, Principal's Office"));
        panel.add(Box.createVerticalStrut(18));

        JButton libraryButton = createPrimaryButton("Library");
        libraryButton.addActionListener(event -> openLibraryGui());

        JButton trainingButton = createPrimaryButton("Training Ground");
        trainingButton.addActionListener(event -> openTrainingGui());

        JButton officeButton = createPrimaryButton("Principal Office");
        officeButton.addActionListener(event -> openPrincipalOfficeGui());

        JButton shopButton = createPrimaryButton("Shop");
        shopButton.addActionListener(event -> openShopGui());

        JButton backButton = createSecondaryButton("Back to Main Menu");
        backButton.addActionListener(event -> {
            subtitleLabel.setText("Adventure overview.");
            refreshHeroDashboard();
            showScreen(SCREEN_MAIN);
        });

        panel.add(libraryButton);
        panel.add(Box.createVerticalStrut(10));
        panel.add(trainingButton);
        panel.add(Box.createVerticalStrut(10));
        panel.add(officeButton);
        panel.add(Box.createVerticalStrut(10));
        panel.add(shopButton);
        panel.add(Box.createVerticalStrut(18));
        panel.add(backButton);
        panel.add(Box.createVerticalGlue());
        return panel;
    }

    private JPanel buildStoryScreen() {
        JPanel panel = createCardPanel();

        storyTitleLabel.setFont(new Font("Serif", Font.BOLD, 28));
        storyTitleLabel.setForeground(new Color(52, 33, 18));
        storyTitleLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);

        storyProgressLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
        storyProgressLabel.setForeground(new Color(95, 74, 58));
        storyProgressLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);

        storyTextArea.setEditable(false);
        storyTextArea.setLineWrap(true);
        storyTextArea.setWrapStyleWord(true);
        storyTextArea.setFont(new Font("Serif", Font.PLAIN, 18));
        storyTextArea.setBackground(new Color(255, 251, 245));
        storyTextArea.setForeground(new Color(43, 31, 21));
        storyTextArea.setMargin(new Insets(16, 16, 16, 16));

        JScrollPane storyScrollPane = new JScrollPane(storyTextArea);
        storyScrollPane.setAlignmentX(JPanel.LEFT_ALIGNMENT);
        storyScrollPane.setPreferredSize(new Dimension(920, 360));
        storyScrollPane.setMaximumSize(new Dimension(Integer.MAX_VALUE, 360));
        storyScrollPane.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(181, 165, 146), 1),
            BorderFactory.createEmptyBorder(0, 0, 0, 0)
        ));

        storyNextButton = createPrimaryButton("Next");
        storyNextButton.addActionListener(event -> advanceStoryBeat());

        storySkipButton = createSecondaryButton("Skip Story");
        storySkipButton.addActionListener(event -> finishStorySequence("Story skipped. You can replay it anytime from the main menu."));

        storyStartButton = createPrimaryButton("Start Adventure");
        storyStartButton.addActionListener(event -> finishStorySequence("Story complete. Your journey continues."));
        storyStartButton.setVisible(false);

        JButton backButton = createSecondaryButton("Back to Character Select");
        backButton.addActionListener(event -> {
            subtitleLabel.setText("Choose a hero to begin.");
            showScreen(SCREEN_CHARACTER);
        });

        JPanel buttonRow = new JPanel(new GridLayout(1, 4, 10, 0));
        buttonRow.setOpaque(false);
        buttonRow.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));
        buttonRow.add(storyNextButton);
        buttonRow.add(storySkipButton);
        buttonRow.add(storyStartButton);
        buttonRow.add(backButton);

        panel.add(storyTitleLabel);
        panel.add(Box.createVerticalStrut(6));
        panel.add(storyProgressLabel);
        panel.add(Box.createVerticalStrut(14));
        panel.add(storyScrollPane);
        panel.add(Box.createVerticalStrut(14));
        panel.add(buttonRow);
        panel.add(Box.createVerticalGlue());
        return panel;
    }

    private JPanel buildInventoryScreen() {
        JPanel panel = createCardPanel();

        panel.add(createHeading("Inventory"));
        panel.add(Box.createVerticalStrut(8));
        panel.add(inventorySummary);
        panel.add(Box.createVerticalStrut(14));

        panel.add(createInventoryRow("Small Health Potion", smallHealthCount, () -> usePotion("SH")));
        panel.add(Box.createVerticalStrut(8));
        panel.add(createInventoryRow("Medium Health Potion", mediumHealthCount, () -> usePotion("MH")));
        panel.add(Box.createVerticalStrut(8));
        panel.add(createInventoryRow("Large Health Potion", largeHealthCount, () -> usePotion("LH")));
        panel.add(Box.createVerticalStrut(8));
        panel.add(createInventoryRow("Small Mana Potion", smallManaCount, () -> usePotion("SM")));
        panel.add(Box.createVerticalStrut(8));
        panel.add(createInventoryRow("Medium Mana Potion", mediumManaCount, () -> usePotion("MM")));
        panel.add(Box.createVerticalStrut(8));
        panel.add(createInventoryRow("Large Mana Potion", largeManaCount, () -> usePotion("LM")));
        panel.add(Box.createVerticalStrut(18));

        JButton backButton = createSecondaryButton("Back to Main Menu");
        backButton.addActionListener(event -> {
            subtitleLabel.setText("Adventure overview.");
            refreshHeroDashboard();
            showScreen(SCREEN_MAIN);
        });

        panel.add(backButton);
        panel.add(Box.createVerticalGlue());
        return panel;
    }

    private JPanel buildProfileScreen() {
        JPanel panel = createCardPanel();

        panel.add(createHeading("Hero Details"));
        panel.add(Box.createVerticalStrut(12));
        panel.add(createStatLine("Name", profileName));
        panel.add(createStatLine("Class", profileClass));
        panel.add(createStatLine("Weapon", profileWeapon));
        panel.add(createStatLine("Level", profileLevel));
        panel.add(createStatLine("Attack", profileAttack));
        panel.add(createStatLine("Defense", profileDefense));
        panel.add(createStatLine("Speed", profileSpeed));
        panel.add(createStatLine("Skill 1", profileSkill1));
        panel.add(createStatLine("Skill 2", profileSkill2));
        panel.add(createStatLine("Ultimate", profileUltimate));
        panel.add(Box.createVerticalStrut(18));

        JButton backButton = createSecondaryButton("Back to Main Menu");
        backButton.addActionListener(event -> {
            subtitleLabel.setText("Adventure overview.");
            refreshHeroDashboard();
            showScreen(SCREEN_MAIN);
        });

        panel.add(backButton);
        panel.add(Box.createVerticalGlue());
        return panel;
    }

    private JPanel buildBattleScreen() {
        JPanel panel = createCardPanel();

        battleTitleValue.setFont(new Font("Serif", Font.BOLD, 30));
        battleRoundValue.setFont(new Font("SansSerif", Font.BOLD, 14));
        battleRoundValue.setForeground(new Color(95, 74, 58));

        panel.add(battleTitleValue);
        panel.add(Box.createVerticalStrut(4));
        panel.add(battleRoundValue);
        panel.add(Box.createVerticalStrut(12));

        JPanel heroCard = createBattleCard("Hero", battleHeroNameValue, battleHeroHpBar, battleHeroManaBar);
        JPanel enemyCard = createBattleCard("Enemy", battleEnemyNameValue, battleEnemyHpBar, battleEnemyManaBar);
        panel.add(heroCard);
        panel.add(Box.createVerticalStrut(10));
        panel.add(enemyCard);
        panel.add(Box.createVerticalStrut(12));

        battleLogArea.setEditable(false);
        battleLogArea.setLineWrap(true);
        battleLogArea.setWrapStyleWord(true);
        battleLogArea.setFont(new Font("SansSerif", Font.PLAIN, 13));
        battleLogArea.setBackground(new Color(248, 245, 239));
        battleLogArea.setForeground(new Color(33, 29, 24));

        JScrollPane logScroll = new JScrollPane(battleLogArea);
        logScroll.setPreferredSize(new Dimension(920, 220));
        logScroll.setMaximumSize(new Dimension(Integer.MAX_VALUE, 220));
        panel.add(logScroll);
        panel.add(Box.createVerticalStrut(12));

        battleBasicButton = createPrimaryButton("Basic");
        battleSkill1Button = createPrimaryButton("Skill 1");
        battleSkill2Button = createPrimaryButton("Skill 2");
        battleUltimateButton = createPrimaryButton("Ultimate");
        battlePotionButton = createSecondaryButton("Potion");
        battleRunButton = createSecondaryButton("Run");
        battleBackButton = createSecondaryButton("Back to Main");
        battleBackButton.setEnabled(false);

        battleBasicButton.addActionListener(event -> submitBattleAction(0));
        battleSkill1Button.addActionListener(event -> submitBattleAction(1));
        battleSkill2Button.addActionListener(event -> submitBattleAction(2));
        battleUltimateButton.addActionListener(event -> submitBattleAction(3));
        battlePotionButton.addActionListener(event -> submitBattleAction(4));
        battleRunButton.addActionListener(event -> submitBattleAction(5));
        battleBackButton.addActionListener(event -> {
            subtitleLabel.setText("Adventure overview.");
            refreshHeroDashboard();
            showScreen(SCREEN_MAIN);
        });

        JPanel row1 = new JPanel(new GridLayout(1, 2, 10, 0));
        row1.setOpaque(false);
        row1.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));
        row1.add(battleBasicButton);
        row1.add(battleSkill1Button);

        JPanel row2 = new JPanel(new GridLayout(1, 2, 10, 0));
        row2.setOpaque(false);
        row2.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));
        row2.add(battleSkill2Button);
        row2.add(battleUltimateButton);

        JPanel row3 = new JPanel(new GridLayout(1, 2, 10, 0));
        row3.setOpaque(false);
        row3.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));
        row3.add(battlePotionButton);
        row3.add(battleRunButton);

        JPanel row4 = new JPanel(new GridLayout(1, 1, 10, 0));
        row4.setOpaque(false);
        row4.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        row4.add(battleBackButton);

        panel.add(row1);
        panel.add(Box.createVerticalStrut(8));
        panel.add(row2);
        panel.add(Box.createVerticalStrut(8));
        panel.add(row3);
        panel.add(Box.createVerticalStrut(8));
        panel.add(row4);
        panel.add(Box.createVerticalGlue());

        return panel;
    }

    private JPanel createBattleCard(String label, JLabel name, JProgressBar hp, JProgressBar mana) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 240));
        card.setBackground(new Color(247, 243, 237));
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(181, 165, 146), 1),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));

        JLabel title = new JLabel(label);
        title.setFont(new Font("SansSerif", Font.BOLD, 14));
        name.setForeground(new Color(52, 33, 18));

        hp.setStringPainted(true);
        hp.setForeground(new Color(164, 54, 54));
        mana.setStringPainted(true);
        mana.setForeground(new Color(52, 92, 156));

        card.add(title);
        card.add(Box.createVerticalStrut(2));
        card.add(name);
        if ("Enemy".equals(label)) {
            card.add(Box.createVerticalStrut(8));
            card.add(createEnemySpritePanel());
        }
        card.add(Box.createVerticalStrut(8));
        card.add(new JLabel("HP"));
        card.add(hp);
        card.add(Box.createVerticalStrut(6));
        card.add(new JLabel("Mana"));
        card.add(mana);
        return card;
    }

    private JPanel createEnemySpritePanel() {
        JPanel spritePanel = new JPanel(new BorderLayout());
        spritePanel.setOpaque(true);
        spritePanel.setBackground(new Color(234, 227, 218));
        spritePanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(181, 165, 146), 1),
            BorderFactory.createEmptyBorder(6, 6, 6, 6)
        ));
        spritePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 116));
        spritePanel.setPreferredSize(new Dimension(160, 116));

        battleEnemySpriteLabel.setHorizontalAlignment(SwingConstants.CENTER);
        battleEnemySpriteLabel.setVerticalAlignment(SwingConstants.CENTER);
        battleEnemySpriteLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
        battleEnemySpriteLabel.setForeground(new Color(95, 74, 58));

        spritePanel.add(battleEnemySpriteLabel, BorderLayout.CENTER);
        return spritePanel;
    }

    private JPanel buildHeroSummaryCard() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(247, 243, 237));
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(150, 123, 95), 1),
            BorderFactory.createEmptyBorder(14, 14, 14, 14)
        ));

        heroNameValue.setFont(new Font("SansSerif", Font.BOLD, 20));
        heroClassValue.setForeground(new Color(99, 78, 63));
        heroLevelValue.setForeground(new Color(99, 78, 63));
        heroGoldValue.setForeground(new Color(99, 78, 63));

        hpBar.setStringPainted(true);
        hpBar.setForeground(new Color(164, 54, 54));
        manaBar.setStringPainted(true);
        manaBar.setForeground(new Color(52, 92, 156));

        panel.add(heroNameValue);
        panel.add(Box.createVerticalStrut(4));
        panel.add(heroClassValue);
        panel.add(heroLevelValue);
        panel.add(heroGoldValue);
        panel.add(Box.createVerticalStrut(10));
        panel.add(new JLabel("Health"));
        panel.add(hpBar);
        panel.add(Box.createVerticalStrut(8));
        panel.add(new JLabel("Mana"));
        panel.add(manaBar);

        return panel;
    }

    private JPanel createInventoryRow(String name, JLabel countLabel, Runnable action) {
        JPanel row = new JPanel(new BorderLayout(12, 0));
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));
        row.setBackground(new Color(247, 243, 237));
        row.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(181, 165, 146), 1),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));

        JLabel nameLabel = new JLabel(name);
        countLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JButton useButton = createSecondaryButton("Use");
        useButton.setPreferredSize(new Dimension(84, 30));
        useButton.addActionListener(event -> action.run());

        JPanel right = new JPanel(new GridLayout(1, 2, 10, 0));
        right.setOpaque(false);
        right.add(countLabel);
        right.add(useButton);

        row.add(nameLabel, BorderLayout.CENTER);
        row.add(right, BorderLayout.EAST);
        return row;
    }

    private JPanel createStatLine(String label, JLabel value) {
        JPanel row = new JPanel(new BorderLayout());
        row.setOpaque(false);
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 28));

        JLabel name = new JLabel(label);
        name.setForeground(new Color(95, 74, 58));

        value.setHorizontalAlignment(SwingConstants.RIGHT);

        row.add(name, BorderLayout.WEST);
        row.add(value, BorderLayout.EAST);
        return row;
    }

    private JPanel createCharacterButton(String title, String description, Runnable action) {
        JPanel wrapper = new JPanel();
        wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.Y_AXIS));
        wrapper.setBackground(new Color(247, 243, 237));
        wrapper.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(150, 123, 95), 1),
            BorderFactory.createEmptyBorder(12, 12, 12, 12)
        ));
        wrapper.setAlignmentX(JPanel.LEFT_ALIGNMENT);

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 22));

        JLabel bodyLabel = createBody("<html>" + description + "</html>");

        // Add the character image here for each selection card.
        // Example:
        // JLabel imageLabel = new JLabel(new ImageIcon("assets/images/characters/swordsman.png"));
        // imageLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);
        // wrapper.add(imageLabel);
        // wrapper.add(Box.createVerticalStrut(10));
        // If you want different images per hero, update each createCharacterButton(...)
        // call in buildCharacterScreen() and pass the correct image path into this method.

        JButton chooseButton = createPrimaryButton("Play " + title);
        chooseButton.addActionListener(event -> action.run());

        wrapper.add(titleLabel);
        wrapper.add(Box.createVerticalStrut(6));
        if (description != null && !description.isBlank()) {
            wrapper.add(bodyLabel);
            wrapper.add(Box.createVerticalStrut(10));
        } else {
            wrapper.add(Box.createVerticalStrut(2));
        }
        wrapper.add(chooseButton);
        return wrapper;
    }

    private JPanel createCardPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(117, 92, 68), 1),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        panel.setBackground(new Color(255, 251, 245));
        return panel;
    }

    private JLabel createHeading(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Serif", Font.BOLD, 28));
        label.setForeground(new Color(52, 33, 18));
        return label;
    }

    private JLabel createBody(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("SansSerif", Font.PLAIN, 14));
        label.setForeground(new Color(95, 74, 58));
        return label;
    }

    private JButton createPrimaryButton(String text) {
        return createStyledButton(
            text,
            primaryButtonSkin,
            new Color(103, 68, 41),
            new Color(255, 245, 230),
            new Font("SansSerif", Font.BOLD, 14),
            42
        );
    }

    private JButton createSecondaryButton(String text) {
        return createStyledButton(
            text,
            secondaryButtonSkin,
            new Color(229, 219, 205),
            new Color(42, 31, 20),
            new Font("SansSerif", Font.BOLD, 13),
            40
        );
    }

    private JButton createStyledButton(String text, ButtonSkin skin, Color fallbackColor, Color textColor, Font font, int height) {
        PixelButton button = new PixelButton(text, skin, fallbackColor);
        button.setForeground(textColor);
        button.setFont(font);
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, height));
        button.setMinimumSize(new Dimension(160, height));
        button.setPreferredSize(new Dimension(220, height));
        button.setAlignmentX(JButton.LEFT_ALIGNMENT);
        return button;
    }

    private ButtonSkin loadButtonSkin(String baseName) {
        // Button skins are optional. If only the normal PNG exists, Swing still uses it
        // and falls back to the default state colors for anything missing.
        BufferedImage normal = loadImageAsset(UI_IMAGE_DIRECTORY + baseName + ".png");
        BufferedImage hover = loadImageAsset(UI_IMAGE_DIRECTORY + baseName + "-hover.png");
        BufferedImage pressed = loadImageAsset(UI_IMAGE_DIRECTORY + baseName + "-pressed.png");
        BufferedImage disabled = loadImageAsset(UI_IMAGE_DIRECTORY + baseName + "-disabled.png");
        return new ButtonSkin(normal, hover, pressed, disabled);
    }

    private BufferedImage loadImageAsset(String path) {
        try {
            return ImageIO.read(new File(path));
        } catch (Exception ignored) {
            return null;
        }
    }

    private void updateEnemySprite(Entity enemy) {
        BufferedImage sprite = loadEnemySprite(enemy);
        if (sprite == null) {
            battleEnemySpriteLabel.setIcon(null);
            battleEnemySpriteLabel.setText("No Sprite");
            return;
        }

        battleEnemySpriteLabel.setText("");
        battleEnemySpriteLabel.setIcon(createScaledSpriteIcon(sprite, 96, 96));
    }

    private BufferedImage loadEnemySprite(Entity enemy) {
        if (enemy == null) {
            return null;
        }

        if (enemy instanceof Goblin) {
            return loadImageAsset(ENEMY_IMAGE_DIRECTORY + "goblin.png");
        }

        return null;
    }

    private javax.swing.Icon createScaledSpriteIcon(BufferedImage sprite, int targetWidth, int targetHeight) {
        BufferedImage scaled = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics2D = scaled.createGraphics();
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);

        int drawWidth = sprite.getWidth();
        int drawHeight = sprite.getHeight();
        double scale = Math.min((double) targetWidth / Math.max(1, drawWidth), (double) targetHeight / Math.max(1, drawHeight));
        int scaledWidth = Math.max(1, (int) Math.round(drawWidth * scale));
        int scaledHeight = Math.max(1, (int) Math.round(drawHeight * scale));
        int x = (targetWidth - scaledWidth) / 2;
        int y = (targetHeight - scaledHeight) / 2;

        graphics2D.drawImage(sprite, x, y, scaledWidth, scaledHeight, null);
        graphics2D.dispose();
        return new javax.swing.ImageIcon(scaled);
    }

    private static final class ButtonSkin {
        private final BufferedImage normal;
        private final BufferedImage hover;
        private final BufferedImage pressed;
        private final BufferedImage disabled;

        private ButtonSkin(BufferedImage normal, BufferedImage hover, BufferedImage pressed, BufferedImage disabled) {
            this.normal = normal;
            this.hover = hover;
            this.pressed = pressed;
            this.disabled = disabled;
        }

        private BufferedImage resolve(ButtonModel model, boolean rollover) {
            if (!model.isEnabled()) {
                return disabled != null ? disabled : normal;
            }
            if (model.isPressed()) {
                if (pressed != null) {
                    return pressed;
                }
                if (hover != null) {
                    return hover;
                }
            }
            if (rollover && hover != null) {
                return hover;
            }
            return normal;
        }

        private boolean hasAnyImage() {
            return normal != null || hover != null || pressed != null || disabled != null;
        }
    }

    private static final class PixelButton extends JButton {
        private final ButtonSkin skin;
        private final Color fallbackColor;
        private boolean rollover;

        private PixelButton(String text, ButtonSkin skin, Color fallbackColor) {
            super(text);
            this.skin = skin;
            this.fallbackColor = fallbackColor;

            setFocusPainted(false);
            setContentAreaFilled(false);
            setBorderPainted(false);
            setOpaque(false);
            setBorder(BorderFactory.createEmptyBorder(10, 18, 10, 18));
            setHorizontalTextPosition(JButton.CENTER);
            setVerticalTextPosition(JButton.CENTER);
            setRolloverEnabled(true);

            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent event) {
                    rollover = true;
                    repaint();
                }

                @Override
                public void mouseExited(MouseEvent event) {
                    rollover = false;
                    repaint();
                }
            });
        }

        @Override
        protected void paintComponent(Graphics graphics) {
            Graphics2D graphics2D = (Graphics2D) graphics.create();

            if (skin != null && skin.hasAnyImage()) {
                BufferedImage stateImage = skin.resolve(getModel(), rollover);
                if (stateImage != null) {
                    // Pixel art should stay crisp, so we scale with nearest-neighbor
                    // instead of the default smoothing used for photos.
                    graphics2D.setRenderingHint(
                        RenderingHints.KEY_INTERPOLATION,
                        RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR
                    );
                    graphics2D.drawImage(stateImage, 0, 0, getWidth(), getHeight(), null);
                }
            } else {
                graphics2D.setColor(resolveFallbackColor());
                graphics2D.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
            }

            graphics2D.dispose();
            super.paintComponent(graphics);
        }

        private Color resolveFallbackColor() {
            if (!isEnabled()) {
                return fallbackColor.darker();
            }
            if (getModel().isPressed()) {
                return fallbackColor.darker();
            }
            if (rollover) {
                return fallbackColor.brighter();
            }
            return fallbackColor;
        }
    }

    private JPanel buildLandingScreen() {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics graphics) {
                super.paintComponent(graphics);

                Graphics2D graphics2D = (Graphics2D) graphics.create();
                graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

                if (landingBackground != null) {
                    // Adjust these crop values if your new background image needs a
                    // different framing on the main screen.
                    // We trim part of the top and bottom of the source image so the title
                    // screen focuses on the center of the artwork before scaling it to fit
                    // the current window size.
                    int sourceY = 150;
                    int sourceHeight = Math.max(1, landingBackground.getHeight() - 210);
                    graphics2D.drawImage(
                        landingBackground,
                        0,
                        0,
                        getWidth(),
                        getHeight(),
                        0,
                        sourceY,
                        landingBackground.getWidth(),
                        sourceY + sourceHeight,
                        null
                    );
                } else {
                    // Fallback background used when no asset file is available.
                    graphics2D.setColor(new Color(27, 13, 16));
                    graphics2D.fillRect(0, 0, getWidth(), getHeight());
                }

                // Dark overlay keeps the title and buttons readable even when the image
                // behind them is bright or detailed.
                graphics2D.setColor(new Color(11, 5, 8, 180));
                graphics2D.fillRect(0, 0, getWidth(), getHeight());
                graphics2D.dispose();
            }
        };

        panel.setLayout(new BorderLayout());

        JPanel content = new JPanel();
        content.setOpaque(false);
        content.setBorder(BorderFactory.createEmptyBorder(70, 80, 70, 80));
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        JLabel eyebrow = new JLabel("REALMS OF RIFTBORNE");
        eyebrow.setFont(new Font("SansSerif", Font.BOLD, 18));
        eyebrow.setForeground(new Color(234, 190, 137));
        eyebrow.setAlignmentX(JLabel.LEFT_ALIGNMENT);

        JLabel title = new JLabel("Mystvale Academy");
        title.setFont(new Font("Serif", Font.BOLD, 56));
        title.setForeground(new Color(255, 238, 216));
        title.setAlignmentX(JLabel.LEFT_ALIGNMENT);

        JLabel body = new JLabel("<html>Defeat Kim Morvain and escape Mystvale Academy.</html>");
        body.setFont(new Font("SansSerif", Font.PLAIN, 18));
        body.setForeground(new Color(232, 219, 208));
        body.setAlignmentX(JLabel.LEFT_ALIGNMENT);

        JButton startButton = createPrimaryButton("Play the Game");
        startButton.setPreferredSize(new Dimension(220, 46));
        startButton.setMaximumSize(new Dimension(220, 46));
        startButton.addActionListener(event -> openCharacterSelectFromLanding());

        JButton exitButton = createSecondaryButton("Exit");
        exitButton.setPreferredSize(new Dimension(220, 44));
        exitButton.setMaximumSize(new Dimension(220, 44));
        exitButton.addActionListener(event -> frame.dispose());

        content.add(Box.createVerticalGlue());
        content.add(eyebrow);
        content.add(Box.createVerticalStrut(10));
        content.add(title);
        content.add(Box.createVerticalStrut(14));
        content.add(body);
        content.add(Box.createVerticalStrut(26));
        content.add(startButton);
        content.add(Box.createVerticalStrut(10));
        content.add(exitButton);
        content.add(Box.createVerticalGlue());

        panel.add(content, BorderLayout.WEST);
        return panel;
    }

    private void wireConsoleStreams() {
        System.setIn(gameInputStream);

        PrintStream guiOut = new PrintStream(
            new BufferedOutputStream(new TextAreaOutputStream(outputArea, this::filterLegacyOutput, this::observeLegacyOutput), 1),
            true,
            StandardCharsets.UTF_8
        );

        System.setOut(guiOut);
        System.setErr(guiOut);
    }

    private void selectHero(Hero selectedHero) {
        hero = selectedHero;
        hero.setHpCap(hero.getHp());
        hero.setManaCap(hero.getMana());
        refreshHeroDashboard();
        refreshInventoryPanel();
        refreshProfilePanel();
        appendLog("\nHero selected: " + hero.getName() + " the " + hero.getCharClass() + ".\n");
        beginStoryForHero(true);
    }

    private void refreshHeroDashboard() {
        if (hero == null) {
            heroNameValue.setText("-");
            heroClassValue.setText("-");
            heroLevelValue.setText("-");
            heroGoldValue.setText("-");
            hpBar.setMaximum(1);
            hpBar.setValue(0);
            hpBar.setString("0 / 0");
            manaBar.setMaximum(1);
            manaBar.setValue(0);
            manaBar.setString("0 / 0");
            return;
        }

        heroNameValue.setText(hero.getName());
        heroClassValue.setText("Class: " + hero.getCharClass());
        heroLevelValue.setText("Level: " + hero.getLevel());
        heroGoldValue.setText("Gold: " + statFormat.format(hero.getGold()));

        hpBar.setMaximum(Math.max(1, hero.getHpCap()));
        hpBar.setValue(Math.max(0, hero.getHp()));
        hpBar.setString(statFormat.format(hero.getHp()) + " / " + statFormat.format(hero.getHpCap()));

        manaBar.setMaximum(Math.max(1, hero.getManaCap()));
        manaBar.setValue(Math.max(0, hero.getMana()));
        manaBar.setString(statFormat.format(hero.getMana()) + " / " + statFormat.format(hero.getManaCap()));

        refreshAreaButtons();
    }

    private void beginStoryForHero(boolean firstTimeSelection) {
        if (hero == null) {
            return;
        }

        currentStorySequence = buildIntroStoryForHero(hero);
        currentStoryIndex = 0;
        storyTitleLabel.setText(firstTimeSelection ? "Opening Story" : "Storyline Recap");
        subtitleLabel.setText(firstTimeSelection ? "Story begins." : "Review your hero's storyline.");
        showCurrentStoryBeat();
        showScreen(SCREEN_STORY);
    }

    private void showCurrentStoryBeat() {
        if (currentStorySequence.length == 0) {
            finishStorySequence("No story scenes are available for this hero yet.");
            return;
        }

        int safeIndex = Math.max(0, Math.min(currentStoryIndex, currentStorySequence.length - 1));
        storyProgressLabel.setText("Scene " + (safeIndex + 1) + " / " + currentStorySequence.length);
        storyTextArea.setText(currentStorySequence[safeIndex]);
        storyTextArea.setCaretPosition(0);

        boolean isLastScene = safeIndex >= currentStorySequence.length - 1;
        storyNextButton.setVisible(!isLastScene);
        storyStartButton.setVisible(isLastScene);
    }

    private void advanceStoryBeat() {
        if (currentStoryIndex < currentStorySequence.length - 1) {
            currentStoryIndex++;
            showCurrentStoryBeat();
        } else {
            finishStorySequence("Story complete. Your journey continues.");
        }
    }

    private void finishStorySequence(String logMessage) {
        subtitleLabel.setText("Adventure overview.");
        refreshHeroDashboard();
        showScreen(SCREEN_MAIN);
        appendLog("\n" + logMessage + "\n");
    }

    private String[] buildIntroStoryForHero(Hero selectedHero) {
        String prologue =
            "Your days as a student have been a blur of half-hearted effort and quiet frustration. " +
            "One restless night, sleep gives way to something stranger. You wake beneath a pale moon, " +
            "facing a vast academy beyond the trees. A wandering spirit named Louraine Aetherlight " +
            "greets you and warns that Mystvale Academy is both sanctuary and trial. To survive, " +
            "you must grow stronger, uncover the truth behind Kim Morvain, and decide what kind of hero you will become.";

        String academy =
            "Mystvale Academy stands at the edge of danger. Within its halls lie the Library, the Training Ground, " +
            "the Principal's Office, and the paths leading toward the Forest of Reverie, Reverie's Edge, and the Forsaken Lands. " +
            "Every step forward reveals more than monsters. The academy hides wounds, secrets, and a fate tied to your chosen hero.";

        if (selectedHero instanceof Swordsman) {
            return new String[] {
                prologue,
                academy,
                "Kael Solmere carries the burden of a bloodline stained by betrayal. The Solmere family once guarded a forbidden power tied to Kim Morvain, " +
                "and the curse left behind still follows their name. Kael enters Mystvale to become stronger, protect the people he loves, and uncover the truth hidden in his family's shadow.",
                "For Kael, this journey is not just about defeating enemies. It is about confronting legacy, loyalty, and the darkness woven into his own blood. " +
                "Every battle at Mystvale brings him closer to either breaking the curse or becoming part of it."
            };
        }

        if (selectedHero instanceof Gunner) {
            return new String[] {
                prologue,
                academy,
                "Aria Caelith was stolen from home and forced into Project LUCENT, a cruel experiment designed by Kim Morvain. " +
                "The trials gave Aria terrifying precision and Aether-fueled power, but they also carved pain deep into body and memory.",
                "Aria arrives in Mystvale carrying anger, survival instinct, and unfinished vengeance. Each step through the academy is a step toward the truth behind Project LUCENT " +
                "and a reckoning with the man who tried to turn Aria into a weapon."
            };
        }

        return new String[] {
            prologue,
            academy,
            "Selene Arclight was born with brilliance in cosmic magic, but her gift drew her into Kim Morvain's schemes. " +
            "She helped shape dangerous magic before discovering the horror it would unleash. By the time she tried to escape, a curse had already marked her.",
            "Selene now walks Mystvale with guilt, pride, and a fierce need to set things right. The road ahead is filled with spells, sacrifice, and buried truths. " +
            "To end Morvain's reign, she must face both the enemy before her and the regret still burning inside."
        };
    }

    private void refreshAreaButtons() {
        if (forestButton == null || swampButton == null || forsakenButton == null) {
            return;
        }

        boolean hasHero = hero != null;
        forestButton.setEnabled(hasHero && hero.hasUnlockedArea1());
        swampButton.setEnabled(hasHero && hero.hasUnlockedArea2());
        forsakenButton.setEnabled(hasHero && hero.hasUnlockedArea3());

        forestButton.setToolTipText(hasHero && !hero.hasUnlockedArea1() ? "Unlock through the Principal Office flow." : null);
        swampButton.setToolTipText(hasHero && !hero.hasUnlockedArea2() ? "Unlock through the Principal Office flow." : null);
        forsakenButton.setToolTipText(hasHero && !hero.hasUnlockedArea3() ? "Unlock through the Principal Office flow." : null);
    }

    private void refreshInventoryPanel() {
        if (hero == null) {
            inventorySummary.setText("No hero selected.");
            smallHealthCount.setText("0");
            mediumHealthCount.setText("0");
            largeHealthCount.setText("0");
            smallManaCount.setText("0");
            mediumManaCount.setText("0");
            largeManaCount.setText("0");
            return;
        }

        Inventory inventory = hero.getInventory();
        inventorySummary.setText(
            "HP " + statFormat.format(hero.getHp()) + "/" + statFormat.format(hero.getHpCap()) +
            "  |  Mana " + statFormat.format(hero.getMana()) + "/" + statFormat.format(hero.getManaCap())
        );
        smallHealthCount.setText(String.valueOf(inventory.getSmallHealthPotion()));
        mediumHealthCount.setText(String.valueOf(inventory.getMediumHealthPotion()));
        largeHealthCount.setText(String.valueOf(inventory.getLargeHealthPotion()));
        smallManaCount.setText(String.valueOf(inventory.getSmallManaPotion()));
        mediumManaCount.setText(String.valueOf(inventory.getMediumManaPotion()));
        largeManaCount.setText(String.valueOf(inventory.getLargeManaPotion()));
    }

    private void refreshProfilePanel() {
        if (hero == null) {
            return;
        }

        profileName.setText(hero.getName());
        profileClass.setText(hero.getCharClass());
        profileWeapon.setText(hero.getWeapon());
        profileLevel.setText(String.valueOf(hero.getLevel()));
        profileAttack.setText(statFormat.format(hero.getAttack()));
        profileDefense.setText(statFormat.format(hero.getDefense()));
        profileSpeed.setText(statFormat.format(hero.getSpeed()));
        profileSkill1.setText(hero.getSkill1());
        profileSkill2.setText(hero.getSkill2());
        profileUltimate.setText(hero.getUltimate());
    }

    private void openLibraryGui() {
        if (!requireHero()) {
            return;
        }

        hero.setVisitedLibrary(true);

        boolean hasQuest1 = !hero.isLibraryQuest1Done();
        boolean hasQuest2 = !hero.isLibraryQuest2Done();

        if (!hasQuest1 && !hasQuest2) {
            JOptionPane.showMessageDialog(frame, "All library quests are already completed.");
            return;
        }

        int questChoice;
        if (hasQuest1 && hasQuest2) {
            Object[] options = {"Lost Book Quest", "Riddle Quest", "Back"};
            questChoice = JOptionPane.showOptionDialog(
                frame,
                "Choose a library quest.",
                "Library",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0]
            );
        } else if (hasQuest1) {
            questChoice = 0;
        } else {
            questChoice = 1;
        }

        if (questChoice == 0 && hasQuest1) {
            int accept = JOptionPane.showConfirmDialog(
                frame,
                "Accept 'Find the Lost Book' quest?",
                "Library Quest",
                JOptionPane.YES_NO_OPTION
            );
            if (accept == JOptionPane.YES_OPTION) {
                hero.setLibraryQuest1Done(true);
                grantRewards(800, 120, "Lost Book quest complete!");
            }
            return;
        }

        if (questChoice == 1 && hasQuest2) {
            int accept = JOptionPane.showConfirmDialog(
                frame,
                "Accept 'Riddles of the Library' quest?",
                "Library Quest",
                JOptionPane.YES_NO_OPTION
            );
            if (accept != JOptionPane.YES_OPTION) {
                return;
            }

            boolean r1 = askRiddle(
                "Riddle 1: I have pages, but I am not a tree. What am I?",
                new Object[] {"Book", "Sword", "Lantern"},
                0
            );
            hero.setRiddle1Done(r1);

            boolean r2 = askRiddle(
                "Riddle 2: I am spent when used to light the way. What am I?",
                new Object[] {"Shadow", "Candle", "Stone"},
                1
            );
            hero.setRiddle2Done(r2);

            boolean r3 = askRiddle(
                "Riddle 3: What grows stronger the more you train?",
                new Object[] {"Luck", "Discipline", "Silence"},
                1
            );
            hero.setRiddle3Done(r3);

            if (hero.isAllRiddlesDone()) {
                hero.setLibraryQuest2Done(true);
                grantRewards(1200, 220, "Riddle quest complete!");
            } else {
                JOptionPane.showMessageDialog(
                    frame,
                    "Not all riddles were correct. Try the quest again.",
                    "Library",
                    JOptionPane.INFORMATION_MESSAGE
                );
            }
        }
    }

    private void openTrainingGui() {
        if (!requireHero()) {
            return;
        }

        hero.setVisitedGym(true);

        if (hero.hasFinishedAllTraining()) {
            JOptionPane.showMessageDialog(frame, "Training is already complete.");
            return;
        }

        while (true) {
            Object[] options = {
                hero.hasFinishedEndurance() ? "Endurance (Done)" : "Endurance",
                hero.hasFinishedStrength() ? "Strength (Done)" : "Strength",
                hero.hasFinishedDurability() ? "Durability (Done)" : "Durability",
                hero.hasFinishedManaRefinement() ? "Mana Refinement (Done)" : "Mana Refinement",
                "Quit Training"
            };

            int choice = JOptionPane.showOptionDialog(
                frame,
                "Complete all 4 trainings to unlock eligibility for Forest of Reverie.",
                "Training Ground",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0]
            );

            if (choice == 4 || choice == JOptionPane.CLOSED_OPTION) {
                if (hero.getNumberOfTrainingFinished() > 0 && hero.getNumberOfTrainingFinished() < 4) {
                    int confirm = JOptionPane.showConfirmDialog(
                        frame,
                        "Quit now? Incomplete training progress will reset.",
                        "Training Ground",
                        JOptionPane.YES_NO_OPTION
                    );

                    if (confirm == JOptionPane.YES_OPTION) {
                        resetTrainingProgress();
                        JOptionPane.showMessageDialog(frame, "Training progress reset.");
                        refreshHeroDashboard();
                        refreshProfilePanel();
                        return;
                    }
                    continue;
                }
                return;
            }

            if (choice == 0 && !hero.hasFinishedEndurance()) {
                resolveTrainingTask("Endurance", () -> hero.setFinishedEndurance(true));
            } else if (choice == 1 && !hero.hasFinishedStrength()) {
                resolveTrainingTask("Strength", () -> hero.setFinishedStrength(true));
            } else if (choice == 2 && !hero.hasFinishedDurability()) {
                resolveTrainingTask("Durability", () -> hero.setFinishedDurability(true));
            } else if (choice == 3 && !hero.hasFinishedManaRefinement()) {
                resolveTrainingTask("Mana Refinement", () -> hero.setFinishedManaRefinement(true));
            }

            if (hero.getNumberOfTrainingFinished() >= 4
                && hero.hasFinishedEndurance()
                && hero.hasFinishedStrength()
                && hero.hasFinishedDurability()
                && hero.hasFinishedManaRefinement()) {
                hero.setFinishedAllTraining(true);
                hero.unlockArea1(true);
                statProgress.endurance(hero);
                statProgress.strength(hero);
                statProgress.durability(hero);
                statProgress.mana(hero);
                grantRewards(2500, 500, "Training complete! You can now apply for Area 1 eligibility.");
                refreshHeroDashboard();
                refreshProfilePanel();
                return;
            }
        }
    }

    private void openShopGui() {
        if (!requireHero()) {
            return;
        }

        hero.setHasVisitedShop(true);
        Inventory inventory = hero.getInventory();

        while (true) {
            String message =
                "Gold: " + statFormat.format(hero.getGold()) + "\n\n" +
                "Select item to buy:";

            Object[] options = {
                "Small HP (450)",
                "Medium HP (1,350)",
                "Large HP (2,750)",
                "Small Mana (450)",
                "Medium Mana (1,350)",
                "Large Mana (2,750)",
                "Back"
            };

            int itemChoice = JOptionPane.showOptionDialog(
                frame,
                message,
                "Shop",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0]
            );

            if (itemChoice == 6 || itemChoice == JOptionPane.CLOSED_OPTION) {
                refreshInventoryPanel();
                refreshHeroDashboard();
                return;
            }

            int[] prices = {450, 1350, 2750, 450, 1350, 2750};
            String[] names = {
                "Small Health Potion",
                "Medium Health Potion",
                "Large Health Potion",
                "Small Mana Potion",
                "Medium Mana Potion",
                "Large Mana Potion"
            };

            Object[] qtyOptions = {"x1", "x5", "x10", "x20", "Cancel"};
            int qtyChoice = JOptionPane.showOptionDialog(
                frame,
                "Choose quantity for " + names[itemChoice] + ".",
                "Shop",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                qtyOptions,
                qtyOptions[0]
            );

            if (qtyChoice == 4 || qtyChoice == JOptionPane.CLOSED_OPTION) {
                continue;
            }

            int[] qtyValues = {1, 5, 10, 20};
            int quantity = qtyValues[qtyChoice];
            int currentCount = getPotionCount(inventory, itemChoice);
            int availableCapacity = inventory.getCapacity() - currentCount;

            if (availableCapacity <= 0) {
                JOptionPane.showMessageDialog(frame, names[itemChoice] + " is already at max capacity.");
                continue;
            }

            if (quantity > availableCapacity) {
                quantity = availableCapacity;
            }

            int totalCost = prices[itemChoice] * quantity;
            if (hero.getGold() < totalCost) {
                JOptionPane.showMessageDialog(frame, "Not enough gold for this purchase.");
                continue;
            }

            int confirm = JOptionPane.showConfirmDialog(
                frame,
                "Buy " + quantity + " " + names[itemChoice] + " for " + statFormat.format(totalCost) + " gold?",
                "Confirm Purchase",
                JOptionPane.YES_NO_OPTION
            );

            if (confirm != JOptionPane.YES_OPTION) {
                continue;
            }

            hero.setGold(hero.getGold() - totalCost);
            setPotionCount(inventory, itemChoice, currentCount + quantity);
            refreshInventoryPanel();
            refreshHeroDashboard();

            JOptionPane.showMessageDialog(
                frame,
                "Purchased " + quantity + " " + names[itemChoice] + ".",
                "Shop",
                JOptionPane.INFORMATION_MESSAGE
            );
        }
    }

    private void openPrincipalOfficeGui() {
        if (!requireHero()) {
            return;
        }

        hero.setVisitedOffice(true);

        if (!hero.hasUnlockedArea1() && hero.canEnterArea1() && hero.hasFinishedAllTraining()) {
            hero.setUnlockArea1(true);
            grantRewards(2500, 500, "Eligibility granted: Forest of Reverie unlocked.");
            refreshHeroDashboard();
            return;
        }

        if (!hero.hasUnlockedArea2() && hero.canEnterArea2() && hero.getHaveDefeatedArea1Boss()) {
            hero.setUnlockArea2(true);
            grantRewards(2500, 500, "Eligibility granted: Reverie's Edge unlocked.");
            refreshHeroDashboard();
            return;
        }

        if (!hero.hasUnlockedArea3() && hero.canEnterArea3() && hero.getHaveDefeatedArea2Boss()) {
            hero.setUnlockArea3(true);
            grantRewards(2500, 500, "Eligibility granted: Forsaken Lands unlocked.");
            refreshHeroDashboard();
            return;
        }

        JOptionPane.showMessageDialog(
            frame,
            "Not eligible yet.\nFinish training and defeat area bosses first.",
            "Principal's Office",
            JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void resolveTrainingTask(String taskName, Runnable markComplete) {
        int start = JOptionPane.showConfirmDialog(
            frame,
            "Start " + taskName + " challenge?",
            "Training Ground",
            JOptionPane.YES_NO_OPTION
        );
        if (start != JOptionPane.YES_OPTION) {
            return;
        }

        boolean success = random.nextInt(10) <= 7;
        if (success) {
            markComplete.run();
            hero.setNumberOfTrainingFinished(hero.getNumberOfTrainingFinished() + 1);
            JOptionPane.showMessageDialog(frame, taskName + " complete!");
        } else {
            JOptionPane.showMessageDialog(frame, taskName + " failed. Try again.");
        }
    }

    private void resetTrainingProgress() {
        hero.setFinishedEndurance(false);
        hero.setFinishedStrength(false);
        hero.setFinishedDurability(false);
        hero.setFinishedManaRefinement(false);
        hero.setFinishedAllTraining(false);
        hero.setNumberOfTrainingFinished(0);
        hero.unlockArea1(false);
    }

    private int getPotionCount(Inventory inventory, int index) {
        return switch (index) {
            case 0 -> inventory.getSmallHealthPotion();
            case 1 -> inventory.getMediumHealthPotion();
            case 2 -> inventory.getLargeHealthPotion();
            case 3 -> inventory.getSmallManaPotion();
            case 4 -> inventory.getMediumManaPotion();
            case 5 -> inventory.getLargeManaPotion();
            default -> 0;
        };
    }

    private void setPotionCount(Inventory inventory, int index, int value) {
        switch (index) {
            case 0 -> inventory.setSmallHealthPotion(value);
            case 1 -> inventory.setMediumHealthPotion(value);
            case 2 -> inventory.setLargeHealthPotion(value);
            case 3 -> inventory.setSmallManaPotion(value);
            case 4 -> inventory.setMediumManaPotion(value);
            case 5 -> inventory.setLargeManaPotion(value);
            default -> {
            }
        }
    }

    private void grantRewards(int gold, int xp, String message) {
        hero.setGold(hero.getGold() + gold);
        hero.levelUp(xp);
        if (hero.getHp() > hero.getHpCap()) {
            hero.setHpCap(hero.getHp());
        }
        if (hero.getMana() > hero.getManaCap()) {
            hero.setManaCap(hero.getMana());
        }
        refreshHeroDashboard();
        refreshInventoryPanel();
        refreshProfilePanel();

        JOptionPane.showMessageDialog(
            frame,
            message + "\nRewards: +" + statFormat.format(gold) + " gold, +" + statFormat.format(xp) + " XP.",
            "Rewards",
            JOptionPane.INFORMATION_MESSAGE
        );
    }

    private boolean askRiddle(String question, Object[] options, int correctIndex) {
        int answer = JOptionPane.showOptionDialog(
            frame,
            question,
            "Library Riddle",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]
        );

        return answer == correctIndex;
    }

    private void usePotion(String itemCode) {
        if (!requireHero()) {
            return;
        }

        Inventory inventory = hero.getInventory();

        switch (itemCode) {
            case "SH":
                if (inventory.getSmallHealthPotion() > 0) {
                    inventory.useSmallHealthPotion(hero);
                }
                break;
            case "MH":
                if (inventory.getMediumHealthPotion() > 0) {
                    inventory.useMediumHealthPotion(hero);
                }
                break;
            case "LH":
                if (inventory.getLargeHealthPotion() > 0) {
                    inventory.useLargeHealthPotion(hero);
                }
                break;
            case "SM":
                if (inventory.getSmallManaPotion() > 0) {
                    inventory.useSmallManaPotion(hero);
                }
                break;
            case "MM":
                if (inventory.getMediumManaPotion() > 0) {
                    inventory.useMediumManaPotion(hero);
                }
                break;
            case "LM":
                if (inventory.getLargeManaPotion() > 0) {
                    inventory.useLargeManaPotion(hero);
                }
                break;
            default:
                break;
        }

        refreshInventoryPanel();
        refreshHeroDashboard();
    }

    private void launchArea1() {
        if (!requireHero()) {
            return;
        }
        if (!hero.hasUnlockedArea1()) {
            JOptionPane.showMessageDialog(frame, "Forest of Reverie is still locked.");
            return;
        }
        startAreaAdventureAsync(this::startForestOfReverieAdventure);
    }

    private void launchArea2() {
        if (!requireHero()) {
            return;
        }
        if (!hero.hasUnlockedArea2()) {
            JOptionPane.showMessageDialog(frame, "Reverie's Edge is still locked.");
            return;
        }
        startAreaAdventureAsync(this::startReveriesEdgeAdventure);
    }

    private void launchArea3() {
        if (!requireHero()) {
            return;
        }
        if (!hero.hasUnlockedArea3()) {
            JOptionPane.showMessageDialog(frame, "Forsaken Lands is still locked.");
            return;
        }
        startAreaAdventureAsync(this::startForsakenLandsAdventure);
    }

    private void startAreaAdventureAsync(Runnable adventureTask) {
        setBattleButtonsEnabled(false);
        battleBackButton.setEnabled(false);

        Thread worker = new Thread(() -> {
            try {
                adventureTask.run();
            } catch (Exception exception) {
                SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(
                    frame,
                    "Area flow error: " + exception.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                ));
            } finally {
                SwingUtilities.invokeLater(() -> {
                    refreshHeroDashboard();
                    refreshInventoryPanel();
                    refreshProfilePanel();
                    if (hero == null) {
                        subtitleLabel.setText("Choose a hero to begin.");
                        showScreen(SCREEN_CHARACTER);
                    } else {
                        subtitleLabel.setText("Adventure overview.");
                        showScreen(SCREEN_MAIN);
                    }
                    setBattleButtonsEnabled(false);
                    battleBackButton.setEnabled(true);
                });
            }
        }, "area-adventure");

        worker.start();
    }

    private void startForestOfReverieAdventure() {
        if (!requireHero()) {
            return;
        }

        hero.setHasVisitedArea1(true);

        Entity[] encounters = {
            new Goblin(),
            new Slime(),
            new MudLurker(),
            new Elderthorn()
        };
        int[] goldRewards = {360, 420, 680, 1200};
        int[] xpRewards = {90, 110, 180, 320};

        boolean completed = runAreaAdventure("Forest of Reverie", encounters, goldRewards, xpRewards);
        if (!completed) {
            return;
        }

        hero.setHaveDefeatedArea1Boss(true);
        hero.unlockArea2(true);
        showInfoSync("Area Cleared", "Forest of Reverie cleared.\nVisit Principal's Office to process Area 2 eligibility.");
    }

    private void startReveriesEdgeAdventure() {
        if (!requireHero()) {
            return;
        }

        hero.setHasVisitedArea2(true);

        Entity[] encounters = {
            new SwampRat(),
            new VeilSerpent(),
            new FadingWarden(),
            new Morgrath()
        };
        int[] goldRewards = {520, 620, 900, 1500};
        int[] xpRewards = {130, 170, 240, 420};

        boolean completed = runAreaAdventure("Reverie's Edge", encounters, goldRewards, xpRewards);
        if (!completed) {
            return;
        }

        hero.setHaveDefeatedArea2Boss(true);
        hero.unlockArea3(true);
        showInfoSync("Area Cleared", "Reverie's Edge cleared.\nVisit Principal's Office to process Area 3 eligibility.");
    }

    private void startForsakenLandsAdventure() {
        if (!requireHero()) {
            return;
        }

        hero.setHasVisitedArea3(true);

        Entity[] encounters = {
            new ShadowAbyss(),
            new VoidBeast(),
            new HollowKing(),
            new Azrael(),
            new Kim()
        };
        int[] goldRewards = {760, 880, 1300, 2000, 4000};
        int[] xpRewards = {210, 260, 360, 650, 1500};

        boolean completed = runAreaAdventure("Forsaken Lands", encounters, goldRewards, xpRewards);
        if (!completed) {
            return;
        }

        hero.setHaveDefeatedArea3Boss(true);

        Object[] options = {"Sacrifice and Return", "Refuse and Repeat Cycle"};
        int endingChoice = showOptionSync(
            "Final Choice",
            "Kim Morvain has fallen.\nChoose your fate:",
            options,
            options[0]
        );

        if (endingChoice == 0) {
            showInfoSync("Ending", "You sacrificed your character and returned to the real world.\nYou cleared the game.");
        } else {
            hero.resetAllProgress();
            hero = null;
            runOnEdtSync(() -> {
                refreshHeroDashboard();
                refreshInventoryPanel();
                refreshProfilePanel();
                subtitleLabel.setText("Choose a hero to begin.");
                showScreen(SCREEN_CHARACTER);
            });
            showInfoSync("Loop Ending", "The cycle repeats. Progress has been reset.");
        }
    }

    private boolean runAreaAdventure(String areaName, Entity[] encounters, int[] goldRewards, int[] xpRewards) {
        showInfoSync(areaName, "Entering " + areaName + ".\nAll combat is button-based.");

        for (int i = 0; i < encounters.length; i++) {
            Entity enemy = encounters[i];
            String header = "Encounter " + (i + 1) + "/" + encounters.length + ": " + enemy.getName();

            int proceed = showConfirmSync(areaName, header + "\nStart battle?");
            if (proceed != JOptionPane.YES_OPTION) {
                showInfoSync(areaName, "Adventure cancelled.");
                return false;
            }

            BattleOutcome outcome = runButtonBattle(enemy, header);
            if (outcome == BattleOutcome.RAN) {
                showInfoSync(areaName, "You retreated from battle.");
                return false;
            }

            if (outcome == BattleOutcome.LOST) {
                showWarningSync("Defeat", "You were defeated and brought back outside the academy.\nArea progress was not completed.");
                return false;
            }

            grantRewards(goldRewards[i], xpRewards[i], enemy.getName() + " defeated!");
        }

        return true;
    }

    private BattleOutcome runButtonBattle(Entity enemy, String battleTitle) {
        if (!requireHero()) {
            return BattleOutcome.LOST;
        }

        int originalHp = hero.getHp();
        int originalMana = hero.getMana();
        int originalCooldown1 = hero.getCooldown1();
        int originalCooldown2 = hero.getCooldown2();
        int originalCooldownU = hero.getCooldownU();
        int originalStun = hero.getStunned();
        int originalPoison = hero.getPoison();

        enemy.setHpCap(enemy.getHp());
        enemy.setManaCap(enemy.getMana());
        int round = 1;
        String battleLog = "Battle started against " + enemy.getName() + ".";

        while (hero.getHp() > 0 && enemy.getHp() > 0) {
            refreshHeroDashboard();
            refreshInventoryPanel();
            refreshProfilePanel();

            if (hero.getStunned() > 0) {
                battleLog = appendBattleLog(battleLog, hero.getName() + " is stunned and loses this turn.");
            } else {
                int choice = chooseBattleAction(enemy, round, battleTitle, battleLog);
                if (choice == JOptionPane.CLOSED_OPTION) {
                    choice = 5;
                }

                if (choice == 4) {
                    usePotionInBattle();
                    continue;
                }

                if (choice == 5) {
                    if (attemptRunAway(enemy)) {
                        restoreHeroBattleState(
                            originalHp,
                            originalMana,
                            originalCooldown1,
                            originalCooldown2,
                            originalCooldownU,
                            originalStun,
                            originalPoison
                        );
                        return BattleOutcome.RAN;
                    }

                    battleLog = appendBattleLog(battleLog, "Retreat failed.");
                } else {
                    String playerActionResult = performPlayerAction(choice, enemy);
                    if (playerActionResult.startsWith("INVALID:")) {
                        showInfoSync("Battle", playerActionResult.replace("INVALID:", "").trim());
                        continue;
                    }
                    battleLog = appendBattleLog(battleLog, playerActionResult);
                }
            }

            applyPlayerAfterTurnEffects();
            reducePlayerCooldowns();

            if (enemy.getHp() <= 0) {
                enemy.setHp(0);
                battleLog = appendBattleLog(battleLog, enemy.getName() + " was defeated.");
                showInfoSync(battleTitle, battleLog);
                restoreHeroBattleState(
                    originalHp,
                    originalMana,
                    originalCooldown1,
                    originalCooldown2,
                    originalCooldownU,
                    originalStun,
                    originalPoison
                );
                return BattleOutcome.WON;
            }

            if (enemy.getStunned() > 0) {
                battleLog = appendBattleLog(battleLog, enemy.getName() + " is stunned and cannot act.");
            } else if (enemy.getDisabled() > 0) {
                battleLog = appendBattleLog(battleLog, enemy.getName() + " is disabled and cannot act.");
            } else {
                battleLog = appendBattleLog(battleLog, performEnemyAction(enemy));
            }

            applyEnemyAfterTurnEffects(enemy);
            reduceEnemyCooldowns(enemy);

            if (hero.getHp() <= 0) {
                hero.setHp(0);
                showWarningSync(battleTitle, battleLog);
                restoreHeroBattleState(
                    originalHp,
                    originalMana,
                    originalCooldown1,
                    originalCooldown2,
                    originalCooldownU,
                    originalStun,
                    originalPoison
                );
                return BattleOutcome.LOST;
            }

            round++;
        }

        restoreHeroBattleState(
            originalHp,
            originalMana,
            originalCooldown1,
            originalCooldown2,
            originalCooldownU,
            originalStun,
            originalPoison
        );
        return hero.getHp() > 0 ? BattleOutcome.WON : BattleOutcome.LOST;
    }

    private int chooseBattleAction(Entity enemy, int round, String battleTitle, String battleLog) {
        runOnEdtSync(() -> {
            battleTitleValue.setText(battleTitle);
            battleRoundValue.setText("Round " + round);
            battleHeroNameValue.setText(hero.getName());
            battleEnemyNameValue.setText(enemy.getName());
            updateEnemySprite(enemy);
            updateBattleBars(enemy);
            battleLogArea.setText(truncateBattleLog(battleLog));
            battleLogArea.setCaretPosition(battleLogArea.getDocument().getLength());
            subtitleLabel.setText("Battle in progress.");
            showScreen(SCREEN_BATTLE);
            setBattleButtonsEnabled(true);
            battleBackButton.setEnabled(false);
        });

        return waitForBattleAction();
    }

    private void updateBattleBars(Entity enemy) {
        battleHeroHpBar.setMaximum(Math.max(1, hero.getHpCap()));
        battleHeroHpBar.setValue(Math.max(0, hero.getHp()));
        battleHeroHpBar.setString(statFormat.format(Math.max(0, hero.getHp())) + " / " + statFormat.format(hero.getHpCap()));

        battleHeroManaBar.setMaximum(Math.max(1, hero.getManaCap()));
        battleHeroManaBar.setValue(Math.max(0, hero.getMana()));
        battleHeroManaBar.setString(statFormat.format(Math.max(0, hero.getMana())) + " / " + statFormat.format(hero.getManaCap()));

        battleEnemyHpBar.setMaximum(Math.max(1, enemy.getHpCap()));
        battleEnemyHpBar.setValue(Math.max(0, enemy.getHp()));
        battleEnemyHpBar.setString(statFormat.format(Math.max(0, enemy.getHp())) + " / " + statFormat.format(enemy.getHpCap()));

        battleEnemyManaBar.setMaximum(Math.max(1, enemy.getManaCap()));
        battleEnemyManaBar.setValue(Math.max(0, enemy.getMana()));
        battleEnemyManaBar.setString(statFormat.format(Math.max(0, enemy.getMana())) + " / " + statFormat.format(enemy.getManaCap()));
    }

    private void submitBattleAction(int action) {
        synchronized (battleActionLock) {
            if (pendingBattleAction != null) {
                return;
            }
            pendingBattleAction = action;
            runOnEdtSync(() -> setBattleButtonsEnabled(false));
            battleActionLock.notifyAll();
        }
    }

    private int waitForBattleAction() {
        synchronized (battleActionLock) {
            pendingBattleAction = null;
            while (pendingBattleAction == null) {
                try {
                    battleActionLock.wait();
                } catch (InterruptedException interruptedException) {
                    Thread.currentThread().interrupt();
                    return 5;
                }
            }
            int action = pendingBattleAction;
            pendingBattleAction = null;
            return action;
        }
    }

    private void setBattleButtonsEnabled(boolean enabled) {
        if (battleBasicButton == null) {
            return;
        }
        battleBasicButton.setEnabled(enabled);
        battleSkill1Button.setEnabled(enabled);
        battleSkill2Button.setEnabled(enabled);
        battleUltimateButton.setEnabled(enabled);
        battlePotionButton.setEnabled(enabled);
        battleRunButton.setEnabled(enabled);
    }

    private void runOnEdtSync(Runnable task) {
        if (SwingUtilities.isEventDispatchThread()) {
            task.run();
            return;
        }

        try {
            SwingUtilities.invokeAndWait(task);
        } catch (Exception ignored) {
        }
    }

    private void showInfoSync(String title, String message) {
        runOnEdtSync(() -> JOptionPane.showMessageDialog(
            frame,
            message,
            title,
            JOptionPane.INFORMATION_MESSAGE
        ));
    }

    private void showWarningSync(String title, String message) {
        runOnEdtSync(() -> JOptionPane.showMessageDialog(
            frame,
            message,
            title,
            JOptionPane.WARNING_MESSAGE
        ));
    }

    private int showConfirmSync(String title, String message) {
        final int[] choice = {JOptionPane.NO_OPTION};
        runOnEdtSync(() -> choice[0] = JOptionPane.showConfirmDialog(
            frame,
            message,
            title,
            JOptionPane.YES_NO_OPTION
        ));
        return choice[0];
    }

    private int showOptionSync(String title, String message, Object[] options, Object initial) {
        final int[] choice = {JOptionPane.CLOSED_OPTION};
        runOnEdtSync(() -> choice[0] = JOptionPane.showOptionDialog(
            frame,
            message,
            title,
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.PLAIN_MESSAGE,
            null,
            options,
            initial
        ));
        return choice[0];
    }

    private String performPlayerAction(int choice, Entity enemy) {
        int enemyHpBefore = enemy.getHp();
        int heroManaBefore = hero.getMana();
        int enemyStunBefore = enemy.getStunned();

        switch (choice) {
            case 0:
                hero.basicAttack(hero, enemy);
                break;
            case 1:
                if (hero.getMana() < hero.scaledCost(hero.getManaCostSkill1())) {
                    return "INVALID:Not enough mana for Skill 1.";
                }
                if (hero.getCooldown1() > 0) {
                    return "INVALID:Skill 1 is on cooldown (" + hero.getCooldown1() + ").";
                }
                hero.skill1(hero, enemy);
                break;
            case 2:
                if (hero.getMana() < hero.scaledCost(hero.getManaCostSkill2())) {
                    return "INVALID:Not enough mana for Skill 2.";
                }
                if (hero.getCooldown2() > 0) {
                    return "INVALID:Skill 2 is on cooldown (" + hero.getCooldown2() + ").";
                }
                hero.skill2(hero, enemy);
                break;
            case 3:
                if (hero.getMana() < hero.scaledCost(hero.getManaCostUltimate())) {
                    return "INVALID:Not enough mana for Ultimate.";
                }
                if (hero.getCooldownU() > 0) {
                    return "INVALID:Ultimate is on cooldown (" + hero.getCooldownU() + ").";
                }
                hero.ultimate(hero, enemy);
                break;
            default:
                return "INVALID:Invalid action.";
        }

        int dealt = Math.max(0, enemyHpBefore - enemy.getHp());
        int manaSpent = Math.max(0, heroManaBefore - hero.getMana());
        boolean stunned = enemy.getStunned() > enemyStunBefore;

        String text = hero.getName() + " dealt " + statFormat.format(dealt) + " to " + enemy.getName() + ".";
        if (manaSpent > 0) {
            text += " Mana -" + statFormat.format(manaSpent) + ".";
        }
        if (stunned) {
            text += " Enemy stunned.";
        }

        return text;
    }

    private String performEnemyAction(Entity enemy) {
        int heroHpBefore = hero.getHp();
        int enemyManaBefore = enemy.getMana();
        int heroStunBefore = hero.getStunned();
        int heroPoisonBefore = hero.getPoison();

        int choice = selectEnemyAction(enemy);

        switch (choice) {
            case 1 -> enemy.basicAttack(enemy, hero);
            case 2 -> enemy.skill1(enemy, hero);
            case 3 -> enemy.skill2(enemy, hero);
            case 4 -> enemy.skill3(enemy, hero);
            case 5 -> enemy.ultimate(enemy, hero);
            default -> enemy.basicAttack(enemy, hero);
        }

        int damage = Math.max(0, heroHpBefore - hero.getHp());
        int manaSpent = Math.max(0, enemyManaBefore - enemy.getMana());
        boolean stunned = hero.getStunned() > heroStunBefore;
        boolean poisoned = hero.getPoison() > heroPoisonBefore;

        String text = enemy.getName() + " dealt " + statFormat.format(damage) + " to " + hero.getName() + ".";
        if (manaSpent > 0) {
            text += " Enemy mana -" + statFormat.format(manaSpent) + ".";
        }
        if (stunned) {
            text += " You are stunned.";
        }
        if (poisoned) {
            text += " You are poisoned.";
        }

        return text;
    }

    private int selectEnemyAction(Entity enemy) {
        int[] pool = new int[5];
        int count = 0;

        pool[count++] = 1;

        if (enemy.getMana() >= enemy.getManaCostSkill1() && enemy.getCooldown1() == 0) {
            pool[count++] = 2;
        }
        if (enemy.getMana() >= enemy.getManaCostSkill2() && enemy.getCooldown2() == 0) {
            pool[count++] = 3;
        }

        boolean hasSkill3 = enemy.getSkill3() != null
            && !enemy.getSkill3().isBlank()
            && !"Unknown".equalsIgnoreCase(enemy.getSkill3());
        if (hasSkill3 && enemy.getMana() >= enemy.getManaCostSkill3() && enemy.getCooldown3() == 0) {
            pool[count++] = 4;
        }

        boolean hasUltimate = enemy.getUltimate() != null
            && !enemy.getUltimate().isBlank()
            && !"Unknown".equalsIgnoreCase(enemy.getUltimate());
        if (hasUltimate && enemy.getMana() >= enemy.getManaCostUltimate() && enemy.getCooldownU() == 0) {
            pool[count++] = 5;
        }

        return pool[random.nextInt(count)];
    }

    private void applyPlayerAfterTurnEffects() {
        if (hero.getPoison() > 0) {
            int poisonDamage = Math.max(1, (int) Math.round(hero.getHpCap() * 0.05));
            hero.setHp(Math.max(0, hero.getHp() - poisonDamage));
            hero.setPoison(hero.getPoison() - 1);
        }

        if (hero.getStunned() >= 0) {
            hero.setStun(hero.getStunned() - 1);
        }

        if (hero.getStunned() < -1) {
            hero.setStun(-1);
        }
        if (hero.getPoison() < 0) {
            hero.setPoison(0);
        }
    }

    private void applyEnemyAfterTurnEffects(Entity enemy) {
        if (enemy.getStunned() >= 0) {
            enemy.setStun(enemy.getStunned() - 1);
        }
        if (enemy.getDisabled() >= 0) {
            enemy.setDisabled(enemy.getDisabled() - 1);
        }
        if (enemy.getStunned() < -1) {
            enemy.setStun(-1);
        }
        if (enemy.getDisabled() < -1) {
            enemy.setDisabled(-1);
        }
    }

    private void reducePlayerCooldowns() {
        if (hero.getCooldown1() > 0) {
            hero.setCooldown1(hero.getCooldown1() - 1);
        }
        if (hero.getCooldown2() > 0) {
            hero.setCooldown2(hero.getCooldown2() - 1);
        }
        if (hero.getCooldownU() > 0) {
            hero.setCooldownU(hero.getCooldownU() - 1);
        }
    }

    private void reduceEnemyCooldowns(Entity enemy) {
        if (enemy.getCooldown1() > 0) {
            enemy.setCooldown1(enemy.getCooldown1() - 1);
        }
        if (enemy.getCooldown2() > 0) {
            enemy.setCooldown2(enemy.getCooldown2() - 1);
        }
        if (enemy.getCooldown3() > 0) {
            enemy.setCooldown3(enemy.getCooldown3() - 1);
        }
        if (enemy.getCooldownU() > 0) {
            enemy.setCooldownU(enemy.getCooldownU() - 1);
        }
    }

    private boolean attemptRunAway(Entity enemy) {
        double baseChance = 30.0;
        double speedFactor = (hero.getSpeed() - enemy.getSpeed()) * 0.5;
        double successChance = Math.max(10.0, Math.min(90.0, baseChance + speedFactor));
        double roll = random.nextDouble() * 100.0;
        return roll <= successChance;
    }

    private void usePotionInBattle() {
        Inventory inventory = hero.getInventory();
        String[] options = {
            "Small HP (" + inventory.getSmallHealthPotion() + ")",
            "Medium HP (" + inventory.getMediumHealthPotion() + ")",
            "Large HP (" + inventory.getLargeHealthPotion() + ")",
            "Small Mana (" + inventory.getSmallManaPotion() + ")",
            "Medium Mana (" + inventory.getMediumManaPotion() + ")",
            "Large Mana (" + inventory.getLargeManaPotion() + ")",
            "Back"
        };

        int choice = showOptionSync("Battle Inventory", "Choose a potion.", options, options[0]);

        if (choice == 6 || choice == JOptionPane.CLOSED_OPTION) {
            return;
        }

        int hpBefore = hero.getHp();
        int manaBefore = hero.getMana();

        switch (choice) {
            case 0 -> {
                if (inventory.getSmallHealthPotion() > 0) {
                    inventory.useSmallHealthPotion(hero);
                } else {
                    showInfoSync("Battle Inventory", "No Small Health Potion left.");
                    return;
                }
            }
            case 1 -> {
                if (inventory.getMediumHealthPotion() > 0) {
                    inventory.useMediumHealthPotion(hero);
                } else {
                    showInfoSync("Battle Inventory", "No Medium Health Potion left.");
                    return;
                }
            }
            case 2 -> {
                if (inventory.getLargeHealthPotion() > 0) {
                    inventory.useLargeHealthPotion(hero);
                } else {
                    showInfoSync("Battle Inventory", "No Large Health Potion left.");
                    return;
                }
            }
            case 3 -> {
                if (inventory.getSmallManaPotion() > 0) {
                    inventory.useSmallManaPotion(hero);
                } else {
                    showInfoSync("Battle Inventory", "No Small Mana Potion left.");
                    return;
                }
            }
            case 4 -> {
                if (inventory.getMediumManaPotion() > 0) {
                    inventory.useMediumManaPotion(hero);
                } else {
                    showInfoSync("Battle Inventory", "No Medium Mana Potion left.");
                    return;
                }
            }
            case 5 -> {
                if (inventory.getLargeManaPotion() > 0) {
                    inventory.useLargeManaPotion(hero);
                } else {
                    showInfoSync("Battle Inventory", "No Large Mana Potion left.");
                    return;
                }
            }
            default -> {
                return;
            }
        }

        int hpGain = Math.max(0, hero.getHp() - hpBefore);
        int manaGain = Math.max(0, hero.getMana() - manaBefore);
        showInfoSync("Battle Inventory", "Potion used.\nHP +" + statFormat.format(hpGain) + " | Mana +" + statFormat.format(manaGain));
        refreshInventoryPanel();
        refreshHeroDashboard();
    }

    private void restoreHeroBattleState(
        int hp,
        int mana,
        int cooldown1,
        int cooldown2,
        int cooldownU,
        int stun,
        int poison
    ) {
        hero.setHp(hp);
        hero.setMana(mana);
        hero.setCooldown1(cooldown1);
        hero.setCooldown2(cooldown2);
        hero.setCooldownU(cooldownU);
        hero.setStun(stun);
        hero.setPoison(poison);
        refreshHeroDashboard();
        refreshInventoryPanel();
        refreshProfilePanel();
    }

    private String appendBattleLog(String current, String line) {
        if (current == null || current.isBlank()) {
            return line;
        }
        return current + "\n" + line;
    }

    private String truncateBattleLog(String log) {
        if (log == null) {
            return "";
        }
        String[] lines = log.split("\\R");
        int start = Math.max(0, lines.length - 6);
        StringBuilder builder = new StringBuilder();
        for (int i = start; i < lines.length; i++) {
            builder.append(lines[i]).append("\n");
        }
        return builder.toString().trim();
    }

    private enum BattleOutcome {
        WON,
        LOST,
        RAN
    }

    private void launchLegacyAction(String title, Runnable action) {
        if (!requireHero()) {
            return;
        }

        appendLog("\n[" + title + "] opened from GUI buttons.\n");

        Thread worker = new Thread(() -> {
            try {
                action.run();
            } catch (Exception exception) {
                exception.printStackTrace();
            } finally {
                SwingUtilities.invokeLater(() -> {
                    refreshHeroDashboard();
                    refreshInventoryPanel();
                    refreshProfilePanel();
                });
            }
        }, "legacy-" + title.toLowerCase().replace(' ', '-'));

        worker.start();
    }

    private boolean requireHero() {
        if (hero != null) {
            return true;
        }

        JOptionPane.showMessageDialog(frame, "Choose a character first.");
        return false;
    }

    private void submitLegacyInput() {
        String rawInput = inputField.getText();

        if (rawInput == null) {
            return;
        }

        String submittedInput = rawInput.strip();

        submitLegacyInputValue(submittedInput);
    }

    private void submitLegacyShortcut(String submittedInput) {
        submitLegacyInputValue(submittedInput);
    }

    private void submitLegacyInputValue(String submittedInput) {
        try {
            gameInputWriter.write((submittedInput + System.lineSeparator()).getBytes(StandardCharsets.UTF_8));
            gameInputWriter.flush();
            appendLog("\n> " + submittedInput + "\n");
            inputField.setText("");
            setQuickChoiceVisible(false);
        } catch (Exception exception) {
            appendLog("\n[Input error] " + exception.getMessage() + "\n");
        }
    }

    private void observeLegacyOutput(String text) {
        if (text == null || text.isEmpty()) {
            return;
        }

        if (!containsYesNoPrompt(text) && quickChoicePanel != null && quickChoicePanel.isVisible()) {
            setQuickChoiceVisible(false);
        }
    }

    private String filterLegacyOutput(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }

        pendingLegacyOutput.append(text);
        StringBuilder visibleOutput = new StringBuilder();

        while (pendingLegacyOutput.length() > 0) {
            MatchResult promptMatch = findPromptMatch(pendingLegacyOutput.toString());

            if (promptMatch == null) {
                int safeLength = Math.max(0, pendingLegacyOutput.length() - LEGACY_OUTPUT_TAIL_LIMIT);

                if (safeLength == 0) {
                    break;
                }

                visibleOutput.append(pendingLegacyOutput, 0, safeLength);
                pendingLegacyOutput.delete(0, safeLength);
                continue;
            }

            if (promptMatch.start > 0) {
                visibleOutput.append(pendingLegacyOutput, 0, promptMatch.start);
                pendingLegacyOutput.delete(0, promptMatch.start);
            }

            String promptBlock = pendingLegacyOutput.substring(0, promptMatch.end - promptMatch.start);
            String promptText = extractPromptText(promptBlock);

            if (promptText != null && !promptText.isBlank()) {
                quickChoicePromptLabel.setText(promptText);
                setQuickChoiceVisible(true);
            }

            pendingLegacyOutput.delete(0, promptBlock.length());
        }

        if (pendingLegacyOutput.length() > 0 && !looksLikePromptPrefix(pendingLegacyOutput.toString())) {
            visibleOutput.append(pendingLegacyOutput);
            pendingLegacyOutput.setLength(0);
        }

        return visibleOutput.toString();
    }

    private void setQuickChoiceVisible(boolean visible) {
        if (quickChoicePanel == null || quickChoicePromptLabel == null) {
            return;
        }

        if (quickChoicePanel.isVisible() == visible && quickChoicePromptLabel.isVisible() == visible) {
            return;
        }

        quickChoicePromptLabel.setVisible(visible);
        quickChoicePanel.setVisible(visible);
        quickChoicePanel.revalidate();
        quickChoicePanel.repaint();
    }

    private MatchResult findPromptMatch(String text) {
        Matcher boxedMatcher = BOXED_YES_NO_PROMPT.matcher(text);

        while (boxedMatcher.find()) {
            String candidate = boxedMatcher.group();

            if (containsYesNoPrompt(candidate)) {
                return new MatchResult(boxedMatcher.start(), boxedMatcher.end());
            }
        }

        Matcher inlineMatcher = INLINE_YES_NO_PROMPT.matcher(text);

        if (inlineMatcher.find() && containsYesNoPrompt(inlineMatcher.group())) {
            return new MatchResult(inlineMatcher.start(), inlineMatcher.end());
        }

        return null;
    }

    private boolean containsYesNoPrompt(String text) {
        return text != null && text.contains("(y/n)");
    }

    private boolean looksLikePromptPrefix(String text) {
        return text.contains("┌") || text.contains("(y/n)") || text.endsWith(": ") || text.endsWith("-->| ");
    }

    private String extractPromptText(String promptBlock) {
        String[] lines = promptBlock.split("\\R");

        for (String line : lines) {
            if (!line.contains("(y/n)")) {
                continue;
            }

            String cleaned = line
                .replace("│", "")
                .replace("(y/n)", "")
                .replace(":", "")
                .replace("-->|", "")
                .trim();

            if (!cleaned.isEmpty()) {
                return cleaned;
            }
        }

        return "Choose:";
    }

    private static final class MatchResult {
        private final int start;
        private final int end;

        private MatchResult(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    private void appendLog(String text) {
        outputArea.append(text);
        outputArea.setCaretPosition(outputArea.getDocument().getLength());
    }

    private void showLandingScreen() {
        titleLabel.setText("Mystvale Academy");
        subtitleLabel.setText("GUI RPG");
        rootLayout.show(rootPanel, ROOT_LANDING);
    }

    private void openCharacterSelectFromLanding() {
        titleLabel.setText("Mystvale Academy");
        subtitleLabel.setText("Choose a hero to begin.");
        rootLayout.show(rootPanel, ROOT_GAME);
        showScreen(SCREEN_CHARACTER);
    }

    private void showScreen(String screenName) {
        screenLayout.show(screenPanel, screenName);
    }
}
