package com.example.data

import com.example.model.IndustrySolution
import com.example.model.PortfolioProject
import com.example.model.ProcessStep
import com.example.model.SavedQuote
import com.example.model.ServiceFeature
import com.example.model.StudioService
import com.example.model.TechTool
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

object StudioRepository {

    val studioContactNumber = "+92 319 9774681"
    val studioWhatsappClean = "923199774681"
    val studioLocation = "Suhan Islamabad, Pakistan"
    val studioStatus = "Taking New Commissions"
    val responseTime = "Within 24 Hours"

    val services: List<StudioService> = listOf(
        StudioService(
            id = "web-apps",
            name = "Websites & Apps Design",
            shortTag = "Web & Mobile",
            category = "web-app",
            tagline = "Modern, responsive websites and app interfaces using AI-powered tools, WordPress, and Hostinger.",
            description = "We design modern, responsive websites and app interfaces using AI-powered design tools, WordPress, and modern web technologies. We create business websites, restaurant websites, e-commerce stores, company websites, clinics, and online shops.",
            heroHighlight = "AI-assisted design, WordPress customization, Hostinger hosting, and mobile-first responsive architecture.",
            deliverables = listOf(
                "AI-assisted website design and development",
                "WordPress website setup and customization",
                "Business, restaurant, company, and e-commerce websites",
                "Mobile-responsive website layouts across all devices",
                "Website pages, menus, forms, and contact sections",
                "Plugin installation and security configuration",
                "Domain and hosting setup using Hostinger Cloud",
                "Basic SEO and website performance optimization",
                "Full QA testing, launch, and handover"
            ),
            techOrMaterials = listOf("WordPress", "Hostinger", "AI Website Builders", "React 19 & Next.js", "Mobile Responsive", "SEO Optimization"),
            estimatedTurnaround = "4 to 8 Weeks",
            startingPrice = "PKR 250,000",
            startingPriceNumeric = 250000,
            features = listOf(
                ServiceFeature("WordPress & AI-Assisted Architecture", "Clean theme customization, page builder flexibility, and AI-accelerated layout design tailored to your industry."),
                ServiceFeature("Hostinger Hosting Setup", "Assistance with domain connection, SSL security certificate, business email setup, and server configuration."),
                ServiceFeature("Responsive & Fast Loading", "Calibrated for lightning speed on smartphones, tablets, laptops, and desktop computers.")
            ),
            accentColorHex = 0xFFF59E0B
        ),
        StudioService(
            id = "logo-design",
            name = "Logo Design",
            shortTag = "Logo Identity",
            category = "logo",
            tagline = "Professional, memorable, and unique logos using CorelDRAW, Adobe Illustrator, and AI tools.",
            description = "We create professional, memorable, and unique logos using CorelDRAW, Adobe Illustrator, and AI-assisted creative tools to establish a strong brand identity.",
            heroHighlight = "Precision vector logo marks, geometric monograms, wordmarks, and resolution-independent masters.",
            deliverables = listOf(
                "Custom bespoke logo design concepts",
                "Brand color palette (CMYK, RGB, HEX)",
                "Typography selection & hierarchy guidelines",
                "Logo variations (Primary, Horizontal, Icon Mark)",
                "High-resolution transparent PNG & JPG files",
                "Print-ready PDF vector exports",
                "Editable CorelDRAW (.CDR) & Illustrator (.AI) master files"
            ),
            techOrMaterials = listOf("CorelDRAW 2024", "Adobe Illustrator", "AI Creative Tools", "Vector Bezier Curves", "CMYK & RGB Calibration"),
            estimatedTurnaround = "5 to 7 Days",
            startingPrice = "PKR 45,000",
            startingPriceNumeric = 45000,
            features = listOf(
                ServiceFeature("CorelDRAW Vector Precision", "Sharp mathematical vectors that never pixelate, scalable from social avatars to giant billboards."),
                ServiceFeature("Brand-Focused Typography", "Pairings of modern fonts, letter-spacing, and hierarchy that convey trust and authority."),
                ServiceFeature("Complete Master Package", "Delivered in transparent PNG, vector SVG, print PDF, and editable CorelDRAW / AI files.")
            ),
            accentColorHex = 0xFFEC4899
        ),
        StudioService(
            id = "business-cards",
            name = "Business Card Design",
            shortTag = "Print & Cards",
            category = "business-card",
            tagline = "Premium business cards, corporate stationery, and letterheads ready for professional printing.",
            description = "We design premium business cards, corporate stationery, letterheads, and professional print-ready designs using CorelDRAW and Adobe Illustrator.",
            heroHighlight = "Front & back custom layouts, print bleeds, logo integration, and high-resolution master files.",
            deliverables = listOf(
                "Custom double-sided business card layout",
                "Front and back executive styling",
                "Logo and corporate brand integration",
                "Print-ready files with standard press bleeds (3mm)",
                "High-resolution PDF/X-1a, JPG, and PNG formats",
                "Editable source files in CorelDRAW or Illustrator"
            ),
            techOrMaterials = listOf("CorelDRAW", "Adobe Illustrator", "Print Press Bleeds (3mm)", "CMYK Color Standards", "High-Res PDF/X"),
            estimatedTurnaround = "3 to 5 Days",
            startingPrice = "PKR 35,000",
            startingPriceNumeric = 35000,
            features = listOf(
                ServiceFeature("Print-Ready Standardization", "Pre-set with 300 DPI resolution, CMYK color profiles, cut-lines, and press bleeds."),
                ServiceFeature("Corporate Identity Consistency", "Aligned with your logo, color codes, phone numbers, WhatsApp, and social handles."),
                ServiceFeature("Flexible Source Formats", "Provided in editable CorelDRAW (.CDR) or Illustrator (.AI) plus instant print PDFs.")
            ),
            accentColorHex = 0xFF8B5CF6
        ),
        StudioService(
            id = "products-packaging",
            name = "Products & Packaging Design",
            shortTag = "Packaging & Labels",
            category = "product",
            tagline = "Attractive product labels, packaging layouts, and photorealistic mockups using CorelDRAW & AI.",
            description = "We create attractive product labels, packaging, product mockups, and promotional visuals using CorelDRAW, Adobe Illustrator, and AI design tools.",
            heroHighlight = "Custom product packaging dielines, regulatory bottle labels, box layouts, and 3D mockups.",
            deliverables = listOf(
                "Custom structural product & box design",
                "Packaging dieline layouts with cut & fold lines",
                "Regulatory compliant labels and stickers",
                "Photorealistic 3D product mockups",
                "Brand color calibration across substrates",
                "Print-ready PDF files for packaging printers",
                "High-resolution presentation renders",
                "Layered editable source files in CorelDRAW & AI"
            ),
            techOrMaterials = listOf("CorelDRAW", "Adobe Illustrator", "AI Image Tools", "Dieline Measurements", "Barcode Placement"),
            estimatedTurnaround = "7 to 10 Days",
            startingPrice = "PKR 85,000",
            startingPriceNumeric = 85000,
            features = listOf(
                ServiceFeature("Shelf & E-Commerce Impact", "Visual styling designed to capture consumer attention on physical retail shelves and online catalogs."),
                ServiceFeature("Accurate Packaging Dielines", "Precision cut-and-fold guidelines tailored to your box, pouch, bottle, or jar specs."),
                ServiceFeature("Realistic 3D Mockup Presentation", "High-definition digital mockups ready for social media advertising and product presentations.")
            ),
            accentColorHex = 0xFF06B6D4
        ),
        StudioService(
            id = "flex-banner",
            name = "Flex & Banner Design",
            shortTag = "Large-Format Print",
            category = "banner",
            tagline = "Professional flex banners, promotional posters, and large-format print graphics.",
            description = "We design professional flex banners, advertising banners, posters, promotional materials, and print-ready graphics using CorelDRAW and Adobe Illustrator.",
            heroHighlight = "Outdoor PVC flex banners, trade show roll-ups, shop signboards, and event backdrops.",
            deliverables = listOf(
                "Custom outdoor flex and banner designs",
                "High-impact promotional marketing layouts",
                "Brand logo and contact integration",
                "Print-ready large-format files (150-300 DPI scaled)",
                "PDF/TIFF/JPG print formats",
                "Grommet eyelet and pole pocket guide templates",
                "Editable CorelDRAW source files as agreed"
            ),
            techOrMaterials = listOf("CorelDRAW", "Adobe Illustrator", "Large-Format Resolution Calibration", "CMYK Solvent Ink Profiles", "Grommet Guides"),
            estimatedTurnaround = "2 to 4 Days",
            startingPrice = "PKR 30,000",
            startingPriceNumeric = 30000,
            features = listOf(
                ServiceFeature("Long-Distance Readability", "High-contrast typography, bold headlines, and vibrant visuals visible from far distances."),
                ServiceFeature("Large-Format Print Specs", "Calibrated file resolutions suitable for grand printing on vinyl, canvas, PVC, or backlit flex."),
                ServiceFeature("Promotional Versatility", "Designs tailored for shop front fascias, roadside displays, exhibitions, or special events.")
            ),
            accentColorHex = 0xFFF97316
        ),
        StudioService(
            id = "brand-identity",
            name = "Brand Identity Design",
            shortTag = "Full Brand System",
            category = "brand-identity",
            tagline = "Complete brand systems with logos, color palettes, typography, stationery, and guidelines.",
            description = "We develop complete brand identity systems, including logos, color palettes, typography, business cards, stationery, social media graphics, and brand guidelines.",
            heroHighlight = "End-to-end visual branding ensuring absolute consistency across digital and print touchpoints.",
            deliverables = listOf(
                "Comprehensive Brand Identity Manual (PDF)",
                "Primary, secondary, and sub-mark logo lockups",
                "Color palette guidelines with CMYK, RGB, and HEX codes",
                "Font pairings and typographic hierarchy rules",
                "Business card and letterhead layouts",
                "Social media profile, post, and banner templates",
                "Full suite of vector, print, and digital master files"
            ),
            techOrMaterials = listOf("CorelDRAW", "Adobe Illustrator", "Brand Style Guides", "Design Systems", "Digital & Print Assets"),
            estimatedTurnaround = "1 to 2 Weeks",
            startingPrice = "PKR 75,000",
            startingPriceNumeric = 75000,
            features = listOf(
                ServiceFeature("Unified Brand Cohesion", "Ensure every customer touchpoint reflects the same professional tone, color harmony, and quality."),
                ServiceFeature("Clear Usage Guidelines", "Practical brand guidelines showing how to use colors, fonts, and logos correctly on any media."),
                ServiceFeature("Multi-Channel Master Assets", "Ready-to-use templates for Instagram, Facebook, LinkedIn, WhatsApp, and office stationery.")
            ),
            accentColorHex = 0xFF10B981
        )
    )

    val portfolioProjects: List<PortfolioProject> = listOf(
        PortfolioProject(
            id = "creative-labs-web",
            title = "CreativeLabs Responsive Web & App Platform",
            client = "CreativeLabs Global",
            category = "web-app",
            categoryLabel = "Website & App UI Design",
            tagline = "Modern, responsive website and web application built with AI-assisted layout, WordPress, and Hostinger cloud deployment.",
            toolsUsed = "WordPress · Hostinger · React 19 · AI UI Tools",
            deliverables = listOf(
                "Interactive responsive desktop & smartphone layouts",
                "WordPress CMS customization with clean admin dashboard",
                "Hostinger domain setup, SSL security & email configuration",
                "SEO schema markup and 0.72s page transition speeds"
            ),
            format = "Responsive Web Platform & PWA",
            finishOrStack = "WordPress · Hostinger · Mobile Responsive",
            timeline = "4 Weeks",
            accentColorHex = 0xFFF59E0B
        ),
        PortfolioProject(
            id = "apex-crest-logo",
            title = "Apex Dynamics Geometric Monogram & Logo Mark",
            client = "Apex Industrial Technologies",
            category = "logo",
            categoryLabel = "Logo Design",
            tagline = "Precision vector logo created in CorelDRAW featuring geometric balance, optical kerning, and complete color separations.",
            toolsUsed = "CorelDRAW 2024 · Adobe Illustrator · Vector Bezier Math",
            deliverables = listOf(
                "Primary, horizontal, and compact icon mark variations",
                "Pantone coated & uncoated spot color calibrations",
                "High-resolution transparent PNG and print PDF/X-1a files",
                "Editable master CorelDRAW vector files (.CDR / .AI)"
            ),
            format = "Vector Bezier Master Package",
            finishOrStack = "CorelDRAW · Adobe Illustrator · SVG / EPS / CDR",
            timeline = "5 Days",
            accentColorHex = 0xFFEC4899
        ),
        PortfolioProject(
            id = "executive-black-cards",
            title = "Executive Corporate Business Cards & Stationery",
            client = "Khyber Strategic Capital",
            category = "business-card",
            categoryLabel = "Business Card Design",
            tagline = "Ultra-clean double-sided executive cards with 3mm press bleeds, gold foil callouts, and CorelDRAW print layouts.",
            toolsUsed = "CorelDRAW · Adobe Illustrator · CMYK Press Bleeds",
            deliverables = listOf(
                "Double-sided print-ready PDF/X files with crop & bleed marks",
                "Foil mask separation layer and matte finish callouts",
                "High-resolution JPG/PNG proofs for client review",
                "Full typography and vector logo layout source files"
            ),
            format = "Standard 3.5\" x 2\" with 3mm Bleeds",
            finishOrStack = "300 DPI CMYK · CorelDRAW Master",
            timeline = "3 Days",
            accentColorHex = 0xFF8B5CF6
        ),
        PortfolioProject(
            id = "lumina-botanical-packaging",
            title = "Lumina Herbal Skincare Bottle & Box Packaging",
            client = "Lumina Wellness Labs",
            category = "product",
            categoryLabel = "Product & Packaging Design",
            tagline = "Structural folding carton dielines, luxury bottle labels, and 3D product mockup visuals.",
            toolsUsed = "CorelDRAW · Adobe Illustrator · 3D Mockup Tools",
            deliverables = listOf(
                "Production-ready structural dieline with cut and crease guides",
                "Front, back, and side regulatory label layouts",
                "3D photorealistic product presentation mockups",
                "Layered CorelDRAW (.CDR) and Illustrator (.AI) source files"
            ),
            format = "Embossed Folding Carton & Label",
            finishOrStack = "Spot Foil · Barcode Tested · CMYK Substrates",
            timeline = "8 Days",
            accentColorHex = 0xFF06B6D4
        ),
        PortfolioProject(
            id = "grand-highway-flex",
            title = "Metro Expressway Grand-Scale Outdoor Flex Banner",
            client = "Capital Urban Real Estate",
            category = "banner",
            categoryLabel = "Flex & Banner Design",
            tagline = "High-contrast outdoor PVC vinyl advertising banner engineered in CorelDRAW for maximum long-distance impact.",
            toolsUsed = "CorelDRAW · Large-Format DPI Calibration · Solvent Inks",
            deliverables = listOf(
                "Grand-scale print master TIFF & PDF at 150 DPI calibrated scale",
                "Brass eyelet and pole-pocket placement template guide",
                "Digital companion social media banners (1:1 & 16:9)",
                "Editable CorelDRAW source files"
            ),
            format = "20ft x 10ft Outdoor Frontlit Flex",
            finishOrStack = "UV Solvent Inks · Weatherproof Hemming",
            timeline = "3 Days",
            accentColorHex = 0xFFF97316
        ),
        PortfolioProject(
            id = "vanguard-brand-system",
            title = "Vanguard Complete Corporate Brand Identity System",
            client = "Vanguard Logistics & Fleet",
            category = "brand-identity",
            categoryLabel = "Brand Identity",
            tagline = "Full-spectrum visual identity system unifying logos, color codes, typography, letterheads, social assets, and brand rules.",
            toolsUsed = "CorelDRAW · Adobe Illustrator · Brand Guidelines Manual",
            deliverables = listOf(
                "30-page brand guidelines manual covering all logo usages",
                "Primary, secondary, and badge logo lockups",
                "CMYK, RGB, and HEX color code standards",
                "Corporate letterhead, envelope, and email signature templates"
            ),
            format = "Complete Brand Identity Manual & Asset Kit",
            finishOrStack = "Vector Suite + Brand Book PDF",
            timeline = "10 Days",
            accentColorHex = 0xFF10B981
        )
    )

    val industrySolutions: List<IndustrySolution> = listOf(
        IndustrySolution(
            id = "corporate",
            title = "Business & Corporate Websites",
            subtitle = "Authority, Trust & Lead Generation",
            description = "Polished, authoritative digital platforms designed for consultancies, corporate enterprises, service providers, and agencies looking to win client trust.",
            keyFeatures = listOf(
                "Executive team profiles & case studies",
                "Direct inquiry forms & lead capture",
                "SEO-optimized service architecture",
                "SSL encryption & fast response times"
            ),
            recommendedTech = "WordPress or Custom React SPA",
            idealFor = "Law firms, financial services, consulting agencies, B2B companies",
            badge = "B2B & Corporate"
        ),
        IndustrySolution(
            id = "restaurant",
            title = "Restaurant & Cafe Websites",
            subtitle = "Digital Menus, Orders & Table Booking",
            description = "Appetizing, mobile-optimized websites featuring interactive food menus, location maps, WhatsApp ordering, and table reservation systems.",
            keyFeatures = listOf(
                "Interactive categorized food & beverage menus",
                "Direct WhatsApp food ordering integration",
                "Google Maps location & opening hours",
                "High-resolution culinary food photography layouts"
            ),
            recommendedTech = "WordPress + WooCommerce / Mobile-First Layout",
            idealFor = "Cafes, fine dining restaurants, bakeries, cloud kitchens, fast food chains",
            badge = "Hospitality & Dining"
        ),
        IndustrySolution(
            id = "ecommerce",
            title = "E-commerce & Online Stores",
            subtitle = "Catalogs, Cart, Checkout & Inventory",
            description = "High-converting online shopping platforms with product catalogs, filterable categories, shopping carts, secure checkout, and order notifications.",
            keyFeatures = listOf(
                "Product catalog with variants (sizes, colors)",
                "Shopping cart & streamlined mobile checkout",
                "Payment gateway / Cash on Delivery (COD) setup",
                "Automated order confirmation alerts via WhatsApp/Email"
            ),
            recommendedTech = "WordPress + WooCommerce or Headless E-Commerce",
            idealFor = "Fashion brands, electronics retailers, cosmetic brands, consumer goods",
            badge = "Retail & Shopping"
        ),
        IndustrySolution(
            id = "clinic",
            title = "Medical Store & Clinic Websites",
            subtitle = "Healthcare Services & Appointments",
            description = "Clean, reliable, and patient-friendly healthcare websites for clinics, diagnostic centers, dental practices, and retail medical pharmacies.",
            keyFeatures = listOf(
                "Doctor directories & clinical department pages",
                "Online appointment booking and WhatsApp consultation",
                "Pharmacy medicine catalog / prescription inquiries",
                "Emergency contact & patient information hotline"
            ),
            recommendedTech = "WordPress with Appointment Booking System",
            idealFor = "Medical specialists, dental clinics, wellness centers, local pharmacies",
            badge = "Medical & Health"
        ),
        IndustrySolution(
            id = "portfolio",
            title = "Portfolio & Personal Websites",
            subtitle = "Showcase Creative Portfolios & Talent",
            description = "Stunning creative portfolios built to spotlight architects, photographers, designers, models, and executives seeking international clients.",
            keyFeatures = listOf(
                "Full-bleed project galleries and lightbox viewing",
                "Client testimonials & awards showcase",
                "Interactive resume & services breakdown",
                "Fast mobile loading speeds"
            ),
            recommendedTech = "WordPress Custom Portfolio / Next.js",
            idealFor = "Photographers, architects, artists, interior designers, consultants",
            badge = "Creative & Personal"
        ),
        IndustrySolution(
            id = "landing",
            title = "High-Conversion Landing Pages",
            subtitle = "Lead Generation & Ad Campaign Conversion",
            description = "Single-purpose promotional landing pages optimized with sticky CTAs, persuasive copy sections, and fast loading speeds to turn ad traffic into paying customers.",
            keyFeatures = listOf(
                "A/B-ready hero sections & headline hierarchies",
                "Instant WhatsApp consultation click-throughs",
                "Trust badges, customer proof & countdown timers",
                "0.5s ultra-fast mobile loading optimization"
            ),
            recommendedTech = "WordPress Elementor Pro / React Landing",
            idealFor = "Product launches, event registrations, course sales, real estate leads",
            badge = "Marketing & Sales"
        )
    )

    val processSteps: List<ProcessStep> = listOf(
        ProcessStep(
            number = "01",
            title = "Discover",
            tagline = "Needs Assessment & Goals",
            description = "Understand the client's needs, business goals, target audience, and project specifications.",
            inclusions = listOf(
                "Detailed client requirements questionnaire",
                "Competitor analysis & aesthetic moodboarding",
                "Scope agreement, timeline & transparent quote in PKR"
            )
        ),
        ProcessStep(
            number = "02",
            title = "Create",
            tagline = "Concept & Visual Prototyping",
            description = "Use AI tools and professional design software (CorelDRAW & Adobe Illustrator) to design initial layout concepts.",
            inclusions = listOf(
                "AI layout generation & wireframe blueprints",
                "Precision vector drafts in CorelDRAW & Illustrator",
                "Initial visual concepts presented with mockups"
            )
        ),
        ProcessStep(
            number = "03",
            title = "Refine",
            tagline = "Client Feedback & Polish",
            description = "Incorporate client feedback, refine details, optimize typography, colors, responsiveness, and print bleeds.",
            inclusions = listOf(
                "Structured client feedback rounds",
                "300 DPI print calibration & CMYK checks",
                "Mobile and desktop responsive fine-tuning"
            )
        ),
        ProcessStep(
            number = "04",
            title = "Deliver & Launch",
            tagline = "Production Files & Setup",
            description = "Provide final production files, install and customize WordPress on Hostinger, test, launch, and handover.",
            inclusions = listOf(
                "Master vector source files (.CDR, .AI, SVG, PDF)",
                "Hostinger domain linkage, SSL security & email setup",
                "Client-friendly content editing & handover guidelines"
            )
        )
    )

    val techTools: List<TechTool> = listOf(
        TechTool(
            name = "CorelDRAW 2024",
            category = "Vector Graphic Design",
            badge = "Print Standard",
            description = "The industry benchmark for vector drawing, logo crafting, business stationery, packaging dielines, and outdoor flex banners.",
            workflowUse = listOf(
                "Precision mathematical vector curves & logos",
                "Accurate 3mm press bleeds and cutting dielines",
                "Large-format print calibration up to 50ft banners"
            )
        ),
        TechTool(
            name = "Adobe Illustrator",
            category = "Vector Illustration",
            badge = "Brand Standard",
            description = "Vector graphics, branding, typography systems, and professional digital illustrations for cross-platform brand coherence.",
            workflowUse = listOf(
                "Complete brand identity manuals & typography sheets",
                "Vector icons and digital asset suites",
                "High-definition print PDF/X export standards"
            )
        ),
        TechTool(
            name = "WordPress CMS",
            category = "Content Management & Web",
            badge = "Web Platform",
            description = "The world's leading CMS for flexible, responsive, client-manageable corporate, restaurant, clinic, and e-commerce websites.",
            workflowUse = listOf(
                "Custom business, restaurant & medical layouts",
                "WooCommerce online store integration",
                "Client-friendly content editing dashboard"
            )
        ),
        TechTool(
            name = "Hostinger Cloud",
            category = "Hosting & Infrastructure",
            badge = "Cloud Security",
            description = "Reliable, high-speed cloud hosting infrastructure with free SSL encryption, daily backups, and custom business email accounts.",
            workflowUse = listOf(
                "Domain linking and nameserver configuration",
                "Free SSL encryption certificates",
                "Fast SSD storage and 99.9% server uptime"
            )
        ),
        TechTool(
            name = "Modern AI UI Builders",
            category = "Accelerated Prototyping",
            badge = "AI Powered",
            description = "AI-assisted website creation and design that rapidly accelerates layout generation, wireframing, and interactive design.",
            workflowUse = listOf(
                "Rapid UI layout generation & wireframing",
                "Intelligent color palette & section exploration",
                "Mobile-friendly responsive baseline scaffolding"
            )
        )
    )

    // Saved Quotes Flow
    private val _savedQuotes = MutableStateFlow<List<SavedQuote>>(emptyList())
    val savedQuotes: StateFlow<List<SavedQuote>> = _savedQuotes.asStateFlow()

    fun addQuote(quote: SavedQuote) {
        _savedQuotes.value = listOf(quote) + _savedQuotes.value
    }

    fun removeQuote(quoteId: String) {
        _savedQuotes.value = _savedQuotes.value.filter { it.id != quoteId }
    }
}
